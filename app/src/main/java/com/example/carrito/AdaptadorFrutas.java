package com.example.carrito;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdaptadorFrutas extends ArrayAdapter<Fruta> {
	Context ctx;
	int layoutTemplate;
	List<Fruta> arrayList;

	public AdaptadorFrutas(@NonNull Context context, int resource, @NonNull List<Fruta> objects) {
		super(context, resource, objects);
		this.ctx = context;
		this.layoutTemplate = resource;
		this.arrayList = objects;
	}

	public int getLayoutTemplate() {
		return layoutTemplate;
	}

	public void setLayoutTemplate(int layoutTemplate) {
		this.layoutTemplate = layoutTemplate;
	}

	public List<Fruta> getArrayList() {
		return arrayList;
	}

	public void setArrayList(List<Fruta> arrayList) {
		this.arrayList = arrayList;
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		//crea un layout donde cargar cada objeto Averia
		View v = LayoutInflater.from(ctx).inflate(layoutTemplate, parent, false);

		// Obtener la información del elemento de la lista que estoy iterando en este momento
		Fruta elementoActual = arrayList.get(position);

		TextView nombre = (TextView) v.findViewById(R.id.nombre);
		TextView descripcion = (TextView) v.findViewById(R.id.descripcion);
		TextView stock = (TextView) v.findViewById(R.id.stock);
		TextView precio = (TextView) v.findViewById(R.id.precio);
		ImageView imagen = (ImageView) v.findViewById(R.id.imagen);

		try {
			nombre.setText(elementoActual.getNombre());
			descripcion.setText(elementoActual.getDescripcion());
			stock.setText("Stock: " + elementoActual.getStock());
			precio.setText("" + elementoActual.getPrecio() + " €");
			imagen.setImageResource(elementoActual.getImagen());
		} catch (NullPointerException e) {
			nombre = (TextView) v.findViewById(R.id.nombre_grid);
			descripcion = (TextView) v.findViewById(R.id.descripcion_grid);
			imagen = (ImageView) v.findViewById(R.id.imagen_grid);

			nombre.setText(elementoActual.getNombre());
			descripcion.setText(elementoActual.getDescripcion());
			imagen.setImageResource(elementoActual.getImagen());
		}

		return v;
	}
}
