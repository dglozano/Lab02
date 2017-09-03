package gruporebechi_garcialozano.dam.isi.frsf.lab02;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

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
    }

    private void addListeners() {
        tgbtnReservaDelivery.setOnCheckedChangeListener(new ReservaDeliveryTgbtnListener());
        spinnerHorario.setOnItemSelectedListener(new HorarioSpinnerListener());
        switchNotificarReserva.setOnCheckedChangeListener(new NotificarReservaSwitchListener());
        radiogrpTipoPlato.setOnCheckedChangeListener(new TipoPlatoRadiogrpListener());
        listviewItemsPedido.setOnItemClickListener(new ItemsPedidoListViewListener());
        btnAgregar.setOnClickListener(new AgregarBtnListener());
        btnConfirmar.setOnClickListener(new ConfirmarBtnListener());
        btnReiniciar.setOnClickListener(new ReiniciarBtnListener());
    }

    private void crearAdaptadores() {
        //Creamos y seteamos los adapters para el spinner y el listview
        ArrayAdapter<CharSequence> spinnerHorarioAdapter = ArrayAdapter.createFromResource(this,
                R.array.horarios, android.R.layout.simple_spinner_item);
        spinnerHorario.setAdapter(spinnerHorarioAdapter);

        ArrayList<CharSequence> listaItems = new ArrayList<CharSequence>();
        ArrayAdapter<CharSequence> listViewItemsPedidoAdapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_list_item_1,listaItems);
        listviewItemsPedido.setAdapter(listViewItemsPedidoAdapter);
    }

    private class ReservaDeliveryTgbtnListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            //TODO implementar
        }
    }

    private class HorarioSpinnerListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //TODO implementar
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            //TODO implementar
        }
    }

    private class NotificarReservaSwitchListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            //TODO implementar
        }
    }

    private class TipoPlatoRadiogrpListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            //TODO implementar
        }
    }

    private class ItemsPedidoListViewListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //TODO implementar
        }
    }

    private class AgregarBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //TODO implementar
        }
    }

    private class ConfirmarBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //TODO implementar
        }
    }

    private class ReiniciarBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //TODO implementar
        }
    }
}
