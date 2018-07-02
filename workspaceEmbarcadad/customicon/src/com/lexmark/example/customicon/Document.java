package com.lexmark.example.customicon;


public class Document
{

   private String name;
   private String cod;
   private String textName;
   
   
      public Document(String name, String cod, String textName){
      
         this.name = name;
         this.cod = cod;
         this.textName = textName;
            
      }    

      public String getName() {
         return name.trim();
      }

      public void setName(String name) {
      this.name = name;
      }

      public String getCod() {
      return cod.trim();
      }

      public void setCod(String cod) {
      this.cod = cod;
      }

      public String getTextName() {
      return textName.trim();
      }

      public void setTextName(String textName) {
      this.textName = textName;
      }
  

}