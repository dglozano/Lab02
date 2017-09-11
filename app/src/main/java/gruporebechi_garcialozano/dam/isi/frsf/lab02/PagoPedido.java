package gruporebechi_garcialozano.dam.isi.frsf.lab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gruporebechi_garcialozano.dam.isi.frsf.lab02.modelo.Pedido;
import gruporebechi_garcialozano.dam.isi.frsf.lab02.modelo.Tarjeta;

public class PagoPedido extends AppCompatActivity {

    private EditText txtInputNombreApellido;
    private EditText txtInputEmail;
    private EditText txtInputTipoTarjeta;
    private EditText txtInputNumeroTarjeta;
    private EditText txtInputFechaVencimiento;
    private Button btnConfirmarPago;
    private Button btnCancelarPago;

    private String nombreApellido;
    private String email;
    private String tipoTarjeta;
    private String numeroTarjeta;
    private String fechaVencimiento;

    private Pedido pedido;

    Intent intentOrigen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_pedido);
        intentOrigen = getIntent();
        pedido = (Pedido) intentOrigen.getSerializableExtra("pedido");

        getElementosById();
        addListeners();
    }

    private void getElementosById() {
        txtInputNombreApellido = (EditText) findViewById(R.id.txtinput_nombre);
        txtInputEmail = (EditText) findViewById(R.id.txtinput_email);
        txtInputTipoTarjeta = (EditText) findViewById(R.id.txtinput_tipo_tarjeta);
        txtInputNumeroTarjeta = (EditText) findViewById(R.id.txtinput_numero_tarjeta);
        txtInputFechaVencimiento = (EditText) findViewById(R.id.txtinput_fecha_venc);
        btnConfirmarPago = (Button) findViewById(R.id.btn_confirmar_pago);
        btnCancelarPago = (Button) findViewById(R.id.btn_cancelar_pago);
    }

    private void addListeners(){
        btnConfirmarPago.setOnClickListener(new confirmarPagoBtnListener());
        btnCancelarPago.setOnClickListener(new cancelarBtnListener());
    }

    private class cancelarBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            setResult(RESULT_CANCELED,intentOrigen);
            finish();
        }
    }

    private class confirmarPagoBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            getDatosIngresados();
            if(sonDatosValidos()) {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setFechaVencimiento(fechaVencimiento);
                tarjeta.setNombre(tipoTarjeta);
                tarjeta.setNumero(numeroTarjeta);
                pedido.setNombreCliente(nombreApellido);
                pedido.setEmail(email);
                pedido.setTarjeta(tarjeta);

                setResult(RESULT_OK, intentOrigen);
                finish();
            } else {
                Toast.makeText(PagoPedido.this, R.string.campos_incompletos, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getDatosIngresados() {
        nombreApellido = txtInputNombreApellido.getText().toString();
        email = txtInputEmail.getText().toString();
        tipoTarjeta = txtInputTipoTarjeta.getText().toString();
        numeroTarjeta = txtInputNumeroTarjeta.getText().toString();
        fechaVencimiento = txtInputFechaVencimiento.getText().toString();
    }

    private boolean sonDatosValidos() {
        return strNotEmpty(nombreApellido) && strNotEmpty(email) && strNotEmpty(tipoTarjeta) &&
                strNotEmpty(numeroTarjeta) && strNotEmpty(fechaVencimiento);
    }

    private boolean strNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
