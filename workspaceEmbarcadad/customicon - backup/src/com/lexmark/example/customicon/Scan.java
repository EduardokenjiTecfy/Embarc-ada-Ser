package com.lexmark.example.customicon;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;

import com.lexmark.core.BooleanElem;
import com.lexmark.core.IntegerElem;
import com.lexmark.prtapp.profile.BasicProfileContext;
import com.lexmark.prtapp.prompt.PromptException;
import com.lexmark.prtapp.storagedevice.StorageDevice;
import com.lexmark.ui.Constants;
import com.lexmark.ui.DocumentWorkflow;
import com.lexmark.ui.WorkflowFactory;
import com.lexmark.ui.WorkflowSetting;

public class Scan {

	public static boolean exec(StorageDevice DISK, BasicProfileContext context,
			String fileNameOut, String docType,Document doc,String ra,String categoria) throws SocketException,
			IllegalStateException, IOException, Exception {
		Activator.getLog().debug("Entrando no scanner ");
		DocumentWorkflow dw = (DocumentWorkflow) context.getWorkflowFactory()
				.create(WorkflowFactory.DOCUMENT);

		WorkflowSetting fileFormat = dw.getSettingCollection().getSetting(
				"fileFormat");
		fileFormat.setInfo(new IntegerElem(Constants.e_TIFF));
		fileFormat.lock();

		WorkflowSetting color = dw.getSettingCollection().getSetting("color");
		color.setInfo(new IntegerElem(1));
		color.lock();

		WorkflowSetting resolution = dw.getSettingCollection().getSetting(
				"resolution");
		resolution.setInfo(new IntegerElem(75));
		resolution.lock();

		WorkflowSetting contentType = dw.getSettingCollection().getSetting(
				"contentType");
		contentType.setInfo(new IntegerElem(1));
		contentType.lock();

		WorkflowSetting originalMediaSize = dw.getSettingCollection()
				.getSetting("originalMediaSize");
		originalMediaSize.setInfo(new IntegerElem(4));
		originalMediaSize.lock();

		/*
		 * if (docType.indexOf("CPF") >= 0 || docType.indexOf("RG") >= 0) {
		 * 
		 * Activator.getLog().debug("Foi encontrado o CPF");
		 * 
		 * WorkflowSetting color =
		 * dw.getSettingCollection().getSetting("color"); color.setInfo(new
		 * IntegerElem(1)); color.lock();
		 * 
		 * WorkflowSetting resolution =
		 * dw.getSettingCollection().getSetting("resolution");
		 * resolution.setInfo(new IntegerElem(75)); resolution.lock();
		 * 
		 * WorkflowSetting contentType =
		 * dw.getSettingCollection().getSetting("contentType");
		 * contentType.setInfo(new IntegerElem(1)); contentType.lock();
		 * 
		 * WorkflowSetting originalMediaSize =
		 * dw.getSettingCollection().getSetting("originalMediaSize");
		 * originalMediaSize.setInfo(new IntegerElem(4));
		 * originalMediaSize.lock();
		 * 
		 * } else {
		 * 
		 * Activator.getLog().debug("N�o foi encontrado o CPF");
		 * 
		 * WorkflowSetting color =
		 * dw.getSettingCollection().getSetting("color"); color.setInfo(new
		 * IntegerElem(0)); color.lock();
		 * 
		 * WorkflowSetting resolution =
		 * dw.getSettingCollection().getSetting("resolution");
		 * resolution.setInfo(new IntegerElem(150)); resolution.lock();
		 * 
		 * WorkflowSetting monoBity =
		 * dw.getSettingCollection().getSetting("monoBitdepth");
		 * monoBity.setInfo(new IntegerElem(1)); monoBity.lock();
		 * 
		 * WorkflowSetting contentType =
		 * dw.getSettingCollection().getSetting("contentType");
		 * contentType.setInfo(new IntegerElem(0)); contentType.lock();
		 * 
		 * WorkflowSetting originalMediaSize =
		 * dw.getSettingCollection().getSetting("originalMediaSize");
		 * originalMediaSize.setInfo(new IntegerElem(4));
		 * originalMediaSize.lock();
		 * 
		 * }
		 */

		// String fileNameMfp = fileNameOut +
		// getExtenFile(fileFormat.getInfo().intValue());
		WorkflowSetting darkness = dw.getSettingCollection().getSetting(
				"darkness");
		darkness.setInfo(new IntegerElem(3));
		darkness.lock();

		WorkflowSetting backgro = dw.getSettingCollection().getSetting(
				"backgroundRemoval");
		backgro.setInfo(new IntegerElem(-2));
		backgro.lock();

		WorkflowSetting multPag = dw.getSettingCollection().getSetting(
				"multiPageTiff");
		multPag.setInfo(new IntegerElem(1));
		multPag.lock();

		WorkflowSetting scanWorkflow = dw.getSettingCollection().getSetting(
				"scanPreview");
		scanWorkflow.setInfo(new BooleanElem(true));
		
		File file = new File(DISK.getRootPath().toString() + "/" + "teste.tif");

		ScanDocuments scanDoc = new ScanDocuments( file,ra,categoria);

		dw.setConsumer(scanDoc);
		Activator.getLog().debug("----------------------------- Scannner: -----------------------------  " );
		try {
			context.startWorkflow(dw,
					BasicProfileContext.WAIT_FOR_SCAN_COMPLETE);
			new SendFile(file,0,"");
			return true;

		} catch (PromptException e) {
			Activator.getLog().debug("Scan falha: ", e);
			return false;

		}

	}

	private static String getExtenFile(int format) {
		String ext = null;
		if (format == Constants.e_TIFF)
			ext = ".tif";
		else if (format == Constants.e_JPEG)
			ext = ".jpg";
		else if (format == Constants.e_PDF)
			ext = ".pdf";
		else
			ext = ".unknown";
		return ext;
	}
}
