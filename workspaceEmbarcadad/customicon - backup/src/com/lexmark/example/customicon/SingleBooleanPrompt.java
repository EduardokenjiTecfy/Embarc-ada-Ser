package com.lexmark.example.customicon;

import com.lexmark.core.Element;
import com.lexmark.prtapp.profile.VlmlNavigator;
import com.lexmark.prtapp.prompt.DisplayType;
import com.lexmark.prtapp.prompt.PromptEventResult;
import com.lexmark.prtapp.prompt.PromptException;
import com.lexmark.prtapp.prompt.VlmlPrompt;
import com.lexmark.prtapp.prompt.VlmlPromptContext;

public class SingleBooleanPrompt  implements VlmlPrompt  {

	VlmlPromptContext context = null;
	   
	/** Name of the top level VLML layout */
	String vlmlName = "singleBooleanPrompt";
	   
	/** Keep track of how we are dismissed so the profile can know */
	String dismissButton = "";
	
	private String labelFalse = "false";
	private String labelTrue = "true";
	
	
	private boolean resultadoInterno = false;
	
	private String LabelInterna = null;
	
	private static int[][] Cordenadas = {
			{155, 180}, 
			{415, 180}}; 
	
	public SingleBooleanPrompt(){	
	}
	
	
	public String getId() {
		
		return "tecnoset.singleBooleanPrompt";
	}
	
	public String getVlml() {
			/**
		       * The VLML returned from the doGetVlml should NOT include the navigation bar;
		       * it will be added automatically.  If this is not desired, then this method should
		       * just return null, and "getVlml" should be overridden and should be used to produce
		       * the VLML.
		       */
			   StringBuffer screen = new StringBuffer();
		   
	           screen.append("<FixedLayout name=\"Main\">\n");
	           
		       screen.append("<AttachChild xCoordinate = \"5\" yCoordinate = \"10\">\n");
		       screen.append("<GridLayout name=\"promptLabel\" rows=\"1\" columns=\"800\" spacing=\"0\" distribution=\"homogeneous\" horizontalScroll=\"never\" verticalScroll=\"never\">\n");
		       screen.append("<AttachChild top=\"0\" left=\"0\" bottom=\"1\" right=\"799\" xFill=\"expand\" yFill=\"expand\" xPadding=\"1\" yPadding=\"2\">\n");
		       screen.append("<Label name=\"promptLabel\" text=\"" + this.LabelInterna + "\" size=\"medium\" style=\"bold\" justification=\"center\"/>\n");
		       screen.append("</AttachChild>\n");
		       screen.append("</GridLayout>\n");
		       screen.append("</AttachChild>\n");
		       
		       
		       
		       screen.append("<AttachChild xCoordinate=\"" + Cordenadas[0][0] + "\" yCoordinate=\""+ Cordenadas[0][1]+"\">\n");
	           screen.append("<LabeledImageButton name=\"false\" overlaySize=\"medium\" >\n");
	           screen.append("<Normal imageName=\"createBookletCoverPageSetupUp\" text=\""+ this.labelFalse +"\"/>\n");
	           screen.append("<Selected imageName=\"createBookletCoverPageSetupDown\" text=\""+ this.labelFalse +"\"/>\n");
	           screen.append("</LabeledImageButton>\n");
	           screen.append("</AttachChild>\n");
	
	
	
	           screen.append("<AttachChild xCoordinate=\"" + Cordenadas[1][0] + "\" yCoordinate=\""+ Cordenadas[1][1]+"\">\n");
	           screen.append("<LabeledImageButton name=\"true\" overlaySize=\"medium\" >\n");
	           screen.append("<Normal imageName=\"createBookletCoverPageSetupUp\" text=\""+ this.labelTrue +"\"/>\n");
	           screen.append("<Selected imageName=\"createBookletCoverPageSetupDown\" text=\""+ this.labelTrue +"\"/>\n");
	           screen.append("</LabeledImageButton>\n");
	           screen.append("</AttachChild>\n");
	           
			   
			   screen.append("<AttachChild xCoordinate=\"0\" yCoordinate=\"412\">\n");
	           screen.append("<GridLayout name=\"bar\" rows=\"1\" columns=\"800\" spacing=\"0\" distribution=\"homogeneous\" horizontalScroll=\"never\" verticalScroll=\"never\" color=\"ffffff\">\n");
	           screen.append("<AttachChild xFill=\"expandWithPadding\" yFill=\"shrink\" xPadding=\"0\" yPadding=\"0\" left=\"0\" right=\"800\" top=\"0\" bottom=\"1\">\n");
	           screen.append("<Image name=\"separatorBar\" imageName=\"NavRowBottomLine\"/>\n");
	           screen.append("</AttachChild>\n");
	           screen.append("</GridLayout>\n");
	           screen.append("</AttachChild>\n");
	            
	           
	           screen.append("<AttachChild xCoordinate = \"0\" yCoordinate = \"413\">\n");
	           screen.append("<GridLayout name=\"nav$\" rows=\"1\" columns=\"640\" spacing=\"0\" distribution=\"homogeneous\" horizontalScroll=\"never\" verticalScroll=\"never\" color=\"b2b2b2\">\n");
	           screen.append("<AttachChild xFill = \"expand\" yFill = \"shrink\" xPadding = \"0\" yPadding = \"0\" left = \"0\" right = \"800\" top = \"0\" bottom = \"1\">\n");
	
	           
	           screen.append("<BoxLayout name=\"homeRow\" alignment=\"horizontal\" color=\"b2b2b2\">\n");
	
	        /*   screen.append("<AttachChildToTheEnd fill=\"shrink\">\n");
	           screen.append("<LabeledImageButton name=\"Next\" overlayPointSize=\"16\" overlayStyle=\"bold\" state=\"NORMAL\">\n");
	           screen.append("<Normal imageName=\"navRowOptionsUp\" text=\"\"/>\n");
	           screen.append("<Selected imageName=\"navRowOptionsDown\" text=\"\"/>\n");
	           screen.append("<Inactive imageName=\"navRowOptionsNa\"/>\n");
	           screen.append("</LabeledImageButton>\n");
	           screen.append("</AttachChildToTheEnd>\n");*/
	
	           screen.append("<AttachChildToTheEnd fill=\"shrink\">\n");
	           screen.append("<LabeledImageButton name=\"Back\" overlayPointSize=\"16\" overlayStyle=\"bold\" state=\"INACTIVE\">\n");
	           screen.append("<Normal imageName=\"optionsMiddleUp\" text=\"\"/>\n");
	           screen.append("<Selected imageName=\"optionsMiddleDown\" text=\"\"/>\n");
	           screen.append("<Inactive imageName=\"optionsMiddleNa\"/>\n");
	           screen.append("</LabeledImageButton>\n");
	           screen.append("</AttachChildToTheEnd>\n");
	
	           screen.append("<AttachChildToTheEnd fill=\"shrink\">\n");
	           screen.append("<ImageButton name=\"Cancel\">\n");
	
	           screen.append("<Normal imageName=\"homeUp\"/>\n");
	           screen.append("<Selected imageName=\"homeDown\"/>\n");
	
	           screen.append("</ImageButton>\n");
	           screen.append("</AttachChildToTheEnd>\n");
	
	           screen.append("</BoxLayout>\n");
	
	           screen.append("</AttachChild>\n");
	
	           screen.append("</GridLayout>\n");
	
	           screen.append("</AttachChild>\n");
	       
	 
	           screen.append("</FixedLayout>\n");
	
	         
	           return screen.toString();
		      
		      
		}
		
	public void setLabel(String text)
	 {
	     this.LabelInterna = text;
		 
	 }
	
	
	public boolean getSelection(){
		 		 
		 return this.resultadoInterno;
	 
	 }


	public String getDismissButton() {
	
		return dismissButton;
	}


	public DisplayType getDisplayType() {

		return DisplayType.VLML;
	}


	public String getHelp() {
	
		return null;
	}


	public String getLabel() {
	
		return this.LabelInterna ;
	}


	public String getName() {
		return vlmlName;
	}


	public void setHelp(String text) {
	
		
	}


	public void setName(String name) {
		this.vlmlName = name;
		
	}


	public void dismissed() {
			
	}


	public PromptEventResult handleEvent(String component, String event, Element data) throws PromptException {
		
		PromptEventResult result = null;
		
		//Activator.getLog().debug("EasyPrompt: got a VLML event!\n\tComponent = " + component + "\n\tEvent = " + event + "\n\t" + data.print("Data"));
	    
		if(component.endsWith("Main.nav$.homeRow.Cancel"))
	     {
	         // If there are other controls or user input, returning "VALIDATE" will
	         // cause the validate method to get called, and give us a chance to
	         // validate input and possibly stop the prompt from exiting
	         dismissButton = "cancel";
	         result = PromptEventResult.CANCEL;
	         context.cancel();
	         
	     }
		 else if(component.equals("Main.true")){
			 
		    Activator.getLog().debug("Esta igual a Main.true");
		     
			this.resultadoInterno = true;
			result = PromptEventResult.RETURN;
		 
		 }
	     else if (component.equals("Main.false"))
	     {
	    	Activator.getLog().debug("Esta igual a Main.false");
		     
			this.resultadoInterno = false;
			result = PromptEventResult.RETURN;
			
			
		}
		
		return result;
	
	}


	public void init(VlmlPromptContext promptContext, VlmlNavigator navigator)
	{
		
		this.context = promptContext;
		
	}


	public boolean validate() throws PromptException {
		
		return true;
	}
	 
	 
	public void setLabelFalse(String label){
		
		this.labelFalse = label;
		
	}
	
	public void setLabelTrue(String label){
		
		this.labelTrue = label;
		
	}
	
}
