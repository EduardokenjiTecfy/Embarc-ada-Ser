package com.lexmark.example.customicon;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.lexmark.prtapp.scan.ScanCancelledException;
import com.lexmark.prtapp.scan.ScanConsumer;
import com.lexmark.prtapp.scan.ScanData;
 


public class ScanDocuments implements ScanConsumer
{

   private File saveFile = null;

   public ScanDocuments(File file)
   {

      saveFile = file;

   }

   public void consume(ScanData data)
   {

      InputStream is = null;
      FileOutputStream fos = null;
      
      try
      {
         
         fos = new FileOutputStream(saveFile);
         
         while ((is = data.nextImageFile()) != null)
         {
            
            byte[] buff = new byte[16384];
            int bytesRead;
            
            while ((bytesRead = is.read(buff)) != -1)
            {

               fos.write(buff, 0, bytesRead);

            }
            
         }

      }
      catch (ScanCancelledException e)
      {

         
         Activator.getLog().debug("The scan has been CANCELLED!!");

      }
      catch (Exception e)
      {

         Activator.getLog().debug("Exception thrown while scanning", e);

      }
      finally
      {
         try
         {
            if (fos != null)

            fos.close();

         }
         catch (IOException ignore)
         {
            Activator.getLog().debug("Problem closing streams", ignore);

         }
      }

      Activator.getLog().debug("FileSaver.consume: Finished!");

   }

}
