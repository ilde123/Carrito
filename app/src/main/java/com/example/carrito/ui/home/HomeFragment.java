package com.example.carrito.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.carrito.AdaptadorFrutas;
import com.example.carrito.Dialogo;
import com.example.carrito.Fruta;
import com.example.carrito.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements Dialogo.RespuestaDialogo {

	private HomeViewModel homeViewModel;
	List<Fruta> lista;
	ListView listView;
	GridView gridView;

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		homeViewModel =
				ViewModelProviders.of(this).get(HomeViewModel.class);
		View root = inflater.inflate(R.layout.fragment_home, container, false);
		final TextView textView = root.findViewById(R.id.text_home);
		homeViewModel.getText().observe(this, new Observer<String>() {
			@Override
			public void onChanged(@Nullable String s) {
//				textView.setText(s);
			}
		});

		lista = new ArrayList<>();
		lista.add(new Fruta("Naranja", "naranja", R.mipmap.orange));
		lista.add(new Fruta("Plátano", "plátano", R.mipmap.banana));
		lista.add(new Fruta("Manzana", "manzana", R.mipmap.apple));
		lista.add(new Fruta("Cereza", "cereza", R.mipmap.cherry));
		lista.add(new Fruta("Pera", "pera", R.mipmap.pear));
		lista.add(new Fruta("Ciruela", "ciruela", R.mipmap.plum));
		lista.add(new Fruta("Fresa", "fresa", R.mipmap.strawberry));
		lista.add(new Fruta("Mora", "mora", R.mipmap.raspberry));

		getActivity().findViewById(R.id.fab).setVisibility(View.VISIBLE);
		getActivity().findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int numero = lista.size();
				lista.add(new Fruta("Added nº" + (numero + 1), "fruta", R.mipmap.plum));

				AdaptadorFrutas adaptador = null;

				if (listView.getVisibility() == View.VISIBLE) {
					adaptador = (AdaptadorFrutas) listView.getAdapter();
					listView.setAdapter(adaptador);
				}
				else {
					adaptador = (AdaptadorFrutas) gridView.getAdapter();
					gridView.setAdapter(adaptador);
				}
			}
		});

		AdaptadorFrutas adaptador = new AdaptadorFrutas(this.getActivity(), R.layout.fruta, lista);

		listView = (ListView)root.findViewById(R.id.listaFruta);
		gridView = (GridView)root.findViewById(R.id.gridFrutaNueva);

		gridView.setVisibility(View.INVISIBLE);

		listView.setAdapter(adaptador);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
//				<--- AGREGAR PRODUCTO AL CARRITO --->
			}
		});

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				<--- AGREGAR PRODUCTO AL CARRITO --->
			}
		});

		registerForContextMenu(listView);
		registerForContextMenu(gridView);

		return root;
	}

	private void mostrarDialogo() {
		final AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());

		ventana.setTitle("Nueva fruta");
		ventana.setMessage("Nombre");
		final EditText editText = new EditText(getActivity());
		editText.setInputType(InputType.TYPE_CLASS_NUMBER);
		ventana.setView(editText);

		ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String nombre = editText.getText().toString();

				if (nombre.length() > 0) {
					Toast.makeText(getActivity(), nombre, Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(getActivity(), "Debe introducir un nombre", Toast.LENGTH_SHORT).show();
				}
			}
		});

		ventana.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		ventana.show();
	}

	@Override
	public void onRespuesta(String s) {
		Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
	}
}