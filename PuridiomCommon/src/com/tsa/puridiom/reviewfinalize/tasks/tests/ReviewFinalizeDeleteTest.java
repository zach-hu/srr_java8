package com.tsa.puridiom.reviewfinalize.tasks.tests;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;


import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.reviewfinalize.tasks.ReviewFinalizeDelete;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import java.math.BigDecimal;
import java.util.*;
/*
 * Aqui solo estamos probando una tarea especifica
 * No un proceso, solo una de sus tareas
 * Aqui necesitamos tanto el objeto ReviewAnalize como el DBSession,
 * pues estos son requeridos por la clase tarea ReviewFinalizeAdd
 *
 * Ademas aqui necesitamos iniciar la transaccion
 * luego la tarea es ejcutada dentro de la clase Tarea
 * Necesitamos hacer commit y cerrar la transaccion.
 *
 * El manejo de errores no es obligatorio
 */


public class ReviewFinalizeDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			ReviewFinalizeDelete task = new ReviewFinalizeDelete();
			Map incomingRequest = new HashMap();

			ReviewFinalize reviewFinalize = new ReviewFinalize();
			reviewFinalize.setIcHeader(new BigDecimal("14"));
//			reviewFinalize.setStep("16");
//			reviewFinalize.setOwner("Owner1");
//			reviewFinalize.setRevisedBy("Revisor1");
//			reviewFinalize.setCompleted("Y");

			DBSession dbs = new DBSession("TEST");
			dbs.startTransaction(); //aqui se inicia la transaccion

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("reviewFinalizebyid", reviewFinalize);

			//	aqui es donde realmente se realiza alguna accion
			Object result = task.executeTask(incomingRequest);

			// si la tarea se ejecuto correctamente, recien se termina
			// la transaccion con commit y se cierra
			if (task.getStatus() == Status.SUCCEEDED)
			{
				dbs.commit();
				dbs.close();
			}

			System.out.println("RESULT: " + result.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
