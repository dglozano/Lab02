package gruporebechi_garcialozano.dam.isi.frsf.lab02;

import android.os.PersistableBundle;
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

import gruporebechi_garcialozano.dam.isi.frsf.lab02.modelo.Pedido;
import gruporebechi_garcialozano.dam.isi.frsf.lab02.modelo.TipoPlato;
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

    private Pedido pedido;

    private ArrayList<Utils.ElementoMenu> listaPedidos;
    private boolean pedidoListo = false;

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
        pedido = new Pedido();
    }

    private void addListeners() {
        radiogrpTipoPlato.setOnCheckedChangeListener(new TipoPlatoRadiogrpListener());
        btnAgregar.setOnClickListener(new AgregarBtnListener());
        btnConfirmar.setOnClickListener(new PagarBtnListener());
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("pedido",pedido);
        outState.putBoolean("pedido_listo",pedidoListo);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pedido = (Pedido) savedInstanceState.getSerializable("pedido");
        pedidoListo = savedInstanceState.getBoolean("pedido_listo");
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
            if(elementoMenu != null && !pedidoListo){
                Boolean agregarTexto = false;
                switch (elementoMenu.getTipo()) {
                    case POSTRE:
                        if(pedido.getPostre()==null) {
                            pedido.setPostre(elementoMenu);
                            agregarTexto = true;
                        }
                        else mostrarItemYaAgregado(TipoPlato.POSTRE);
                        break;
                    case PRINCIPAL:
                        if(pedido.getPlato()==null) {
                            pedido.setPlato(elementoMenu);
                            agregarTexto = true;
                        }
                        else mostrarItemYaAgregado(TipoPlato.PRINCIPAL);
                        break;
                    case BEBIDA:
                        if(pedido.getBebida()==null) {
                            pedido.setBebida(elementoMenu);
                            agregarTexto = true;
                        }
                        else mostrarItemYaAgregado(TipoPlato.BEBIDA);
                        break;
                }
                if(agregarTexto) {
                    String text = listaPedidos.isEmpty() ? "" : txtDetallesPedido.getText().toString() + "\n";
                    listaPedidos.add(elementoMenu);
                    text += elementoMenu.toString();
                    txtDetallesPedido.setText(text);
                }
            }
            else {
                displayErrores();
            }
        }
    }

    private class PagarBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(!listaPedidos.isEmpty() && !pedidoListo){
                Double total = 0.0;
                pedidoListo = true;
                for(Utils.ElementoMenu e : listaPedidos){
                    total += e.getPrecio();
                }
                pedido.setCosto(total);
                pedido.setEsDelivery(tgbtnReservaDelivery.isChecked());
                pedido.setHoraEntrega(spinnerHorario.getSelectedItem().toString());

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
            pedidoListo = false;
            txtDetallesPedido.scrollTo(0,0);
            radiogrpTipoPlato.check(R.id.radiobtn_plato);
            pedido = new Pedido();
        }
    }

    private void displayErrores() {
        if(pedidoListo) {
            Toast.makeText(MainActivity.this, R.string.error_ya_confirmado, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, R.string.error_empty, Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarItemYaAgregado(TipoPlato tipo) {
        int recurso = 0;
        switch (tipo) {
            case POSTRE:
                recurso = R.string.postre_ya_agregado;
                break;
            case PRINCIPAL:
                recurso = R.string.principal_ya_agregado;
                break;
            case BEBIDA:
                recurso = R.string.bebida_ya_agregada;
                break;
        }
        Toast.makeText(MainActivity.this, recurso, Toast.LENGTH_LONG).show();
    }
}
