package androi.edu.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

public class frmRegistrar extends AppCompatActivity {

    EditText edtnombre,edtapellidos,edtsexo,edtcorreo,edtusuario,edtcontraseña;
    String accion;
    Button btnAgregar,btnCancelar,btnNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_registrar);

        edtnombre = (EditText)findViewById(R.id.txtNombre);
        edtapellidos = (EditText)findViewById(R.id.txtApellidos);
        edtsexo = (EditText)findViewById(R.id.txtSexo);
        edtcorreo = (EditText)findViewById(R.id.txtCorreo);
        edtusuario = (EditText)findViewById(R.id.txtUsuario);
        edtcontraseña = (EditText)findViewById(R.id.txtContraseña);
        btnAgregar = (Button)findViewById(R.id.btnAgregar);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnNuevo = (Button)findViewById(R.id.btnNuevo);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("DADO","EL NUMERO RANDOM ES: " + nombre.toString());
              ejecutarServicio("http://192.168.43.87:80//android/editar.php");

                //ejecutarServicio("http://192.168.56.1/:80/prueba1/insertar.php");
            }
        });

    }
    private void ejecutarServicio(String URL)
    {
        accion = "insertar";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("accion",accion);
                parametros.put("nombre",edtnombre.getText().toString());
                parametros.put("apellidos",edtapellidos.getText().toString());
                parametros.put("sexo",edtsexo.getText().toString());
                parametros.put("correo",edtcorreo.getText().toString());
                parametros.put("usuario",edtusuario.getText().toString());
                parametros.put("contraseña",edtcontraseña.getText().toString());
                return parametros;
        }
        };


        RequestQueue requestQueue= Volley.newRequestQueue(this );
        requestQueue.add(stringRequest);
    }


}
