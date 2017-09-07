package gruporebechi_garcialozano.dam.isi.frsf.lab02;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import gruporebechi_garcialozano.dam.isi.frsf.lab02.modelo.Utils;

public class MainActivity extends AppCompatActivity {

    private ToggleButton tgbtnReservaDelivery;
    private Spinner spinnerHorario;
    private Switch switchNotificarReserva;
    private TextView txtDetallesPedido;
    private RadioGroup radiogrpTipoPlato;
    private ListView listviewItemsPedido;
    private Button btnAgregar;
    private Button btnConfirmar;
    private Button btnReiniciar;

    private Utils utils;

    private Utils.ElementoMenu[] platos;
    private Utils.ElementoMenu[] bebidas;
    private Utils.ElementoMenu[] postres;

    private ArrayList<Utils.ElementoMenu> listaPedidos;
    private boolean pedidoConfirmado = false;

    private ArrayList<Utils.ElementoMenu> listaItems;
    private ItemsPedidoAdapter itemsPedidoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getElementosById();
        inicializarDatos();
        addListeners();
        crearAdaptadores();
    }

    private void getElementosById() {
        tgbtnReservaDelivery = (ToggleButton) findViewById(R.id.tgbtn_reserva_delivery);
        spinnerHorario = (Spinner) findViewById(R.id.spinner_horario);
        switchNotificarReserva = (Switch) findViewById(R.id.switch_notificar_reserva);
        txtDetallesPedido = (TextView) findViewById(R.id.txt_detalles_pedido);
        radiogrpTipoPlato = (RadioGroup) findViewById(R.id.radiogrp_tipo_plato);
        listviewItemsPedido = (ListView) findViewById(R.id.listview_items_pedido);
        btnAgregar = (Button) findViewById(R.id.btn_agregar);
        btnConfirmar = (Button) findViewById(R.id.btn_confirmar);
        btnReiniciar = (Button) findViewById(R.id.btn_reiniciar);
    }

    private void inicializarDatos() {
        utils = new Utils();
        utils.iniciarListas();
        platos = utils.getListaPlatos();
        bebidas = utils.getListaBebidas();
        postres = utils.getListaPostre();
        listaPedidos = new ArrayList<>();
    }

    private void addListeners() {
        radiogrpTipoPlato.setOnCheckedChangeListener(new TipoPlatoRadiogrpListener());
        btnAgregar.setOnClickListener(new AgregarBtnListener());
        btnConfirmar.setOnClickListener(new ConfirmarBtnListener());
        btnReiniciar.setOnClickListener(new ReiniciarBtnListener());
        txtDetallesPedido.setMovementMethod(new ScrollingMovementMethod());
    }

    private void crearAdaptadores() {
        //Creamos y seteamos los adapters para el spinner y el listview
        ArrayAdapter<CharSequence> spinnerHorarioAdapter = ArrayAdapter.createFromResource(this,
                R.array.horarios, android.R.layout.simple_spinner_item);
        spinnerHorario.setAdapter(spinnerHorarioAdapter);

        listaItems = new ArrayList<>(Arrays.asList(platos));
        itemsPedidoAdapter = new ItemsPedidoAdapter(this, listaItems);
        listviewItemsPedido.setAdapter(itemsPedidoAdapter);
    }

    private class TipoPlatoRadiogrpListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
            listaItems.clear();
            switch (id) {
                case R.id.radiobtn_plato:
                    listaItems.addAll(Arrays.asList(platos));
                    break;
                case R.id.radiobtn_bebida:
                    listaItems.addAll(Arrays.asList(bebidas));
                    break;
                case R.id.radiobtn_postre:
                    listaItems.addAll(Arrays.asList(postres));
                    break;
            }
            itemsPedidoAdapter.notifyDataSetChanged();
        }
    }

    private class AgregarBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Utils.ElementoMenu elementoMenu = itemsPedidoAdapter.getSelected();
            if(elementoMenu != null && !pedidoConfirmado){
                String text = listaPedidos.isEmpty() ? "" : txtDetallesPedido.getText().toString() + "\n";
                listaPedidos.add(elementoMenu);
                text += elementoMenu.toString();
                txtDetallesPedido.setText(text);
            }
            else {
                displayErrores();
            }
        }
    }

    private class ConfirmarBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(!listaPedidos.isEmpty() && !pedidoConfirmado){
                Double total = 0.0;
                pedidoConfirmado = true;
                for(Utils.ElementoMenu e : listaPedidos){
                    total += e.getPrecio();
                }
                DecimalFormat df = new DecimalFormat("##.##");
                txtDetallesPedido.setText(txtDetallesPedido.getText().toString() + "\nTotal: $ " + df.format(total));
            }
            else {
                displayErrores();
            }
        }
    }

    private class ReiniciarBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            listaPedidos.clear();
            txtDetallesPedido.setText(R.string.no_hay_items);
            pedidoConfirmado = false;
            txtDetallesPedido.scrollTo(0,0);
            radiogrpTipoPlato.check(R.id.radiobtn_plato);
        }
    }

    private void displayErrores() {
        if(pedidoConfirmado) {
            Toast.makeText(MainActivity.this, R.string.error_ya_confirmado, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, R.string.error_empty, Toast.LENGTH_SHORT).show();
        }
    }
}
