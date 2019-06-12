package androi.edu.android;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SoundPool sp;
    int sonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        sonido = sp.load(this,R.raw.boton_sonido,1);
    }

    public  void siguiente(View view){

        Intent frmLogin = new Intent(this,frmRegistrar.class);
        startActivity(frmLogin);
    }

    public  void llamafrmBuscarUsuario(View view){

        Intent frmLoginBusca = new Intent(this,frmBuscar.class);
        startActivity(frmLoginBusca);
    }

    public void presionar(View view)
    {
        sp.play(sonido,1,1,1,0,0);
    }
}
