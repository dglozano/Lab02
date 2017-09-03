package gruporebechi_garcialozano.dam.isi.frsf.lab02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by diegogarcialozano on 03/09/17.
 */

public class ItemsPedidoAdapter extends ArrayAdapter<Utils.ElementoMenu> {
    LayoutInflater inflater;
    // El adapter hace el trabajo de un RadioGroup
    private RadioButton seleccionadoRadioBtn;
    private int selectedPosition = -1;


    public ItemsPedidoAdapter(Context applicationContext, List<Utils.ElementoMenu> listaItems) {
        super(applicationContext, R.layout.row_plato, listaItems);
        inflater = LayoutInflater.from(applicationContext);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DecimalFormat df = new DecimalFormat("#.##");
        View row = convertView;
        if(row == null) row = inflater.inflate(R.layout.row_plato, parent, false);
        ViewItemHolder holder = (ViewItemHolder) row.getTag();
        if(holder ==null){
            holder = new ViewItemHolder(row);
            row.setTag(holder);
        }

        String nombre = this.getItem(position).getNombre();
        String importe = df.format(this.getItem(position).getPrecio());
        holder.nombreImporteTextView.setText(nombre + " ($ " + importe + ")");

        holder.seleccionadoRadioBtn.setOnClickListener(new radioBtnListener(position));

        // Cuando va a cargando las filas
        if(selectedPosition != position){
            // Si la posicion de la fila es distinta que la seleccionada, pongo en falso el RadioBtn
            holder.seleccionadoRadioBtn.setChecked(false);
        }
        else {
            // Si no, lo pongo checkeado
            holder.seleccionadoRadioBtn.setChecked(true);
            if(seleccionadoRadioBtn != null && holder.seleccionadoRadioBtn != seleccionadoRadioBtn) {
                seleccionadoRadioBtn = holder.seleccionadoRadioBtn;
            }
        }
        return row;
    }

    public void notifyDataSetChanged(){
        selectedPosition = -1;
        seleccionadoRadioBtn = null;
        super.notifyDataSetChanged();
    }

    private class ViewItemHolder {
        TextView nombreImporteTextView = null;
        RadioButton seleccionadoRadioBtn = null;

        ViewItemHolder(View base) {
            this.nombreImporteTextView = base.findViewById(R.id.txtview_nombre_importe);
            this.seleccionadoRadioBtn = base.findViewById(R.id.radiobtn_seleccionado);
        }
    }

    private class radioBtnListener implements View.OnClickListener {
        private final int position;

        public radioBtnListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if(position != selectedPosition && seleccionadoRadioBtn != null) {
                seleccionadoRadioBtn.setChecked(false);
            }
            selectedPosition = position;
            seleccionadoRadioBtn = (RadioButton) view;
        }
    }
}