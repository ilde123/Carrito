package com.example.carrito;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	private AppBarConfiguration mAppBarConfiguration;
	List<Fruta> lista;
	ListView listView;
	GridView grid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		FloatingActionButton fab = findViewById(R.id.fab);
/*		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

			}
		});
*/		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		NavigationView navigationView = findViewById(R.id.nav_view);
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		mAppBarConfiguration = new AppBarConfiguration.Builder(
				R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
				R.id.nav_tools, R.id.nav_share, R.id.nav_send)
				.setDrawerLayout(drawer)
				.build();
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		NavigationUI.setupWithNavController(navigationView, navController);
/*
		lista = new ArrayList<>();
		lista.add(new Fruta("Tomate", "tomate", null));
		lista.add(new Fruta("Limón", "limón", null));
		lista.add(new Fruta("Pera", "pera", null));

		AdaptadorFrutas adaptador = new AdaptadorFrutas(this, R.layout.fruta, lista);
		listView = (ListView)findViewById(R.id.lista);

		listView.setAdapter(adaptador);*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {

		switch (item.getItemId()) {
			case R.id.action_settings:
				listView = (ListView)findViewById(R.id.lista);
				grid = (GridView)findViewById(R.id.grid);

				AdaptadorFrutas adaptador = null;
				try {
					adaptador = (AdaptadorFrutas) listView.getAdapter();
				} catch (NullPointerException e) {
					listView = (ListView)findViewById(R.id.listaFruta);
					grid = (GridView)findViewById(R.id.gridFrutaNueva);

					adaptador = (AdaptadorFrutas) listView.getAdapter();
				}

				if (grid.getVisibility() == View.INVISIBLE) {
					adaptador.setLayoutTemplate(R.layout.fruta_grid);
					grid.setAdapter(adaptador);
					grid.setNumColumns(3);
					grid.setVisibility(View.VISIBLE);
					listView.setVisibility(View.INVISIBLE);
					item.setIcon(R.mipmap.list);
				}
				else {
					adaptador.setLayoutTemplate(R.layout.fruta);
					listView.setAdapter(adaptador);
					listView.setVisibility(View.VISIBLE);
					grid.setVisibility(View.INVISIBLE);
					item.setIcon(R.mipmap.grid);
				}
				break;
			default:
				super.onOptionsItemSelected(item);
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onSupportNavigateUp() {
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		return NavigationUI.navigateUp(navController, mAppBarConfiguration)
				|| super.onSupportNavigateUp();
	}
}
