package com.example.carrito;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.fragment.app.DialogFragment;

public class Dialogo extends DialogFragment {
	RespuestaDialogo respuesta;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
//		respuesta = (RespuestaDialogo)getActivity();

		builder.setView(inflater.inflate(R.layout.dialog_fruta, null))
				.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						respuesta.onRespuesta("hola mundo");
						dialog.cancel();
					}
				});

		return builder.create();
	}

	public interface RespuestaDialogo {
		public void onRespuesta(String s);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {
			respuesta = (RespuestaDialogo)activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement NoticeDialogListener");
		}

	}

/*	public AlertDialog createSimpleDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("Titulo")
				.setMessage("El Mensaje para el usuario")
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// Acciones
							}
						})
				.setNegativeButton("CANCELAR",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// Acciones
							}
						});

		return builder.create();
	}

	public interface OnSimpleDialogListener {
		void onPossitiveButtonClick();// Eventos Botón Positivo
		void onNegativeButtonClick();// Eventos Botón Negativo
	}*/
}