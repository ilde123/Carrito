package com.example.carrito.ui.gallery;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.example.carrito.Fruta;
import com.example.carrito.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

	private GalleryViewModel galleryViewModel;
	List<Fruta> lista;
	ListView listView;
	GridView gridView;

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		galleryViewModel =
				ViewModelProviders.of(this).get(GalleryViewModel.class);
		View root = inflater.inflate(R.layout.fragment_gallery, container, false);
		final TextView textView = root.findViewById(R.id.text_gallery);
		galleryViewModel.getText().observe(this, new Observer<String>() {
			@Override
			public void onChanged(@Nullable String s) {
//				textView.setText(s);
			}
		});

		lista = new ArrayList<>();
/*		lista.add(new Fruta("Naranja", "naranja", R.mipmap.orange));
		lista.add(new Fruta("Plátano", "plátano", R.mipmap.banana));
		lista.add(new Fruta("Manzana", "manzana", R.mipmap.apple));
		lista.add(new Fruta("Cereza", "cereza", R.mipmap.cherry));
		lista.add(new Fruta("Pera", "pera", R.mipmap.pear));
		lista.add(new Fruta("Ciruela", "ciruela", R.mipmap.plum));
		lista.add(new Fruta("Fresa", "fresa", R.mipmap.strawberry));
		lista.add(new Fruta("Mora", "mora", R.mipmap.raspberry));
*/
		getActivity().findViewById(R.id.fab).setVisibility(View.VISIBLE);

		AdaptadorFrutas adaptador = new AdaptadorFrutas(this.getActivity(), R.layout.fruta, lista);
		listView = (ListView)root.findViewById(R.id.lista);
		gridView = (GridView)root.findViewById(R.id.grid);
		gridView.setVisibility(View.INVISIBLE);

		listView.setAdapter(adaptador);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				frutaSelecionada(position);
			}
		});

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				frutaSelecionada(position);
			}
		});

		registerForContextMenu(listView);
		registerForContextMenu(gridView);

		return root;
	}

	private void frutaSelecionada(int position) {
		Fruta fruta = lista.get(position);
		Toast.makeText(getActivity(), fruta.getNombre(), Toast.LENGTH_SHORT).show();

		Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
		i.putExtra(SearchManager.QUERY, fruta.getNombre());
		Intent chooser = new Intent();

		chooser = i.createChooser(i, "Elige aplicación");
		startActivity(chooser);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getActivity().getMenuInflater();
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		menu.setHeaderTitle("Acciones");
		inflater.inflate(R.menu.context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()){
			case R.id.delete_item:
				this.lista.remove(info.position);
				AdaptadorFrutas adaptador = (AdaptadorFrutas) listView.getAdapter();
				adaptador.notifyDataSetChanged();
				Toast.makeText(getActivity(), "Fruta borrada", Toast.LENGTH_SHORT).show();
				return true;
			default:
				return super.onContextItemSelected(item);
		}
	}


/*
	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {

		switch (item.getItemId()) {
			case R.id.action_settings:
				Log.w("Menu item", "Menu item");

				break;
		}

		return super.onOptionsItemSelected(item);
	}
*/
	public void cambiarVista() {
		if (gridView.getVisibility() == View.INVISIBLE) {
			AdaptadorFrutas adaptador = (AdaptadorFrutas) listView.getAdapter();

			gridView.setAdapter(adaptador);
			gridView.setVisibility(View.VISIBLE);
			Toast.makeText(getActivity(), "Vista cambiada", Toast.LENGTH_SHORT).show();
		}
	}
}