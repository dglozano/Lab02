package gruporebechi_garcialozano.dam.isi.frsf.lab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PagoPedido extends AppCompatActivity {

    private EditText txtInputNombreApellido;
    private EditText txtInputEmail;
    private EditText txtInputTipoTarjeta;
    private EditText txtInputNumeroTarjeta;
    private EditText txtInputFechaVencimiento;
    private Button btnConfirmarPago;
    private Button btnCancelarPago;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_pedido);

        getElementosById();

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

            //TODO completar

            Intent iResultado = getIntent();
            setResult(RESULT_OK,iResultado);
            finish();
        }
    }

    private class confirmarPagoBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            //TODO completar

            Intent iResultado = getIntent();
            setResult(RESULT_OK,iResultado);
            finish();
        }
    }
}
