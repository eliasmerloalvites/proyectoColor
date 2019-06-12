package androi.edu.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class frmBuscar extends AppCompatActivity {

    EditText edtnombre,edtapellidos,edtsexo,edtcorreo,edtusuario,edtcontraseña,edtBuscar;
    String acccion;
    Button btnBuscar,btnEditar,btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_buscar);


        edtnombre = (EditText)findViewById(R.id.txtNombre);
        edtapellidos = (EditText)findViewById(R.id.txtApellidos);
        edtsexo = (EditText)findViewById(R.id.txtSexo);
        edtcorreo = (EditText)findViewById(R.id.txtCorreo);
        edtusuario = (EditText)findViewById(R.id.txtUsuario);
        edtcontraseña = (EditText)findViewById(R.id.txtContraseña);
        edtBuscar = (EditText)findViewById(R.id.txtBuscar);

        btnBuscar = (Button) findViewById(R.id.btnBuscar) ;
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);



        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // buscarUsuario("https://dataapp103.000webhostapp.com?codigo="+edtBuscar.getText()+"");

               buscarUsuario("http://192.168.43.87:80//android/buscar.php?codigo="+edtBuscar.getText()+"");
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.d("DADO","EL NUMERO RANDOM ES: " + nombre.toString());
                ejecutarServicEditar("http://192.168.43.87:80//android/editar.php");
               // Limpiar();
                //ejecutarServicio("http://192.168.56.1/:80/prueba1/insertar.php");
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.d("DADO","EL NUMERO RANDOM ES: " + nombre.toString());
                ejecutarServicEliminar("http://192.168.43.87:80//android/editar.php");
                //Limpiar();
                //ejecutarServicio("http://192.168.56.1/:80/prueba1/insertar.php");
            }
        });
    }

    private void buscarUsuario(String URL){


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        edtnombre.setText(jsonObject.getString("usu_Nombre"));
                        edtapellidos.setText(jsonObject.getString("usu_Apellidos"));
                        edtsexo.setText(jsonObject.getString("usu_Sexo"));
                        edtcorreo.setText(jsonObject.getString("usu_correo"));
                        edtusuario.setText(jsonObject.getString("usu_Usuario"));
                        edtcontraseña.setText(jsonObject.getString("usu_Password"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(this );
        requestQueue.add(jsonArrayRequest);

    }

    private void ejecutarServicEditar(String URL)
    {
        acccion = "editar";
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("accion",acccion);
                parametros.put("buscar",edtBuscar.getText().toString());
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

    private void ejecutarServicEliminar(String URL)
    {
        acccion = "eliminar";
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("accion",acccion);
                parametros.put("buscar",edtBuscar.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this );
        requestQueue.add(stringRequest);
    }

   /*  private  void Limpiar()
    {

        ((EditText) findViewById(R.id.txtNombre)).setText("");
        ((EditText) findViewById(R.id.txtApellidos)).setText("");
        ((EditText) findViewById(R.id.txtSexo)).setText("");
        ((EditText) findViewById(R.id.txtCorreo)).setText("");
        ((EditText) findViewById(R.id.txtUsuario)).setText("");
        ((EditText) findViewById(R.id.txtContraseña)).setText("");

       edtnombre.setText("");
        edtnombre.setText("");
        edtapellidos.setText("");
        edtsexo.setText("");
        edtcorreo.setText("");
        edtusuario.setText("");
        edtcontraseña.setText("");
    }*/
}
