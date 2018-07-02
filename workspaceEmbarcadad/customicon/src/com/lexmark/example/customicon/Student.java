package com.lexmark.example.customicon;


public class Student
{

   
   private String CPFALUNO;
   private String RA;
   private String NOMEALUNO;
   private String CODCURSO;
   private String NOMECURSO;
   private String CODCENTRO;
   private String SIGLACENTRO;
   private String NOMECENTRO;
   
   public Student(String cpfaluno, String ra , String nomealuno, String codcurso, String nomecurso, String codcentro, String siglacentro, String nomecentro )
   {
      this.CPFALUNO = cpfaluno;
      this.RA = ra;
      this.NOMEALUNO = nomealuno;
      this.CODCURSO = codcurso;
      this.NOMECURSO = nomecurso;
      this.SIGLACENTRO = siglacentro;
      this.NOMECENTRO = nomecentro;
      this.CODCENTRO = codcentro;
      
   }
  
   public String getCPFALUNO() {
   return CPFALUNO.trim();
   }

   public void setCPFALUNO(String cPFALUNO) {
   this.CPFALUNO = cPFALUNO;
   }

   public String getRA() {
   return RA.trim();
   }

   public void setRA(String rA) {
   this.RA = rA;
   }

   public String getNOMEALUNO() {
   return NOMEALUNO.trim();
   }

   public void setNOMEALUNO(String nOMEALUNO) {
   this.NOMEALUNO = nOMEALUNO;
   }

   public String getCODCURSO() {
   return CODCURSO.trim();
   }

   public void setCODCURSO(String cODCURSO) {
   this.CODCURSO = cODCURSO;
   }

   public String getNOMECURSO() {
   return NOMECURSO;
   }

   public void setNOMECURSO(String nOMECURSO) {
   this.NOMECURSO = nOMECURSO;
   }

   public String getCODCENTRO() {
   return CODCENTRO.trim();
   }

   public void setCODCENTRO(String cODCENTRO) {
   this.CODCENTRO = cODCENTRO;
   }

   public String getSIGLACENTRO() {
   return SIGLACENTRO.trim();
   }

   public void setSIGLACENTRO(String sIGLACENTRO) {
   this.SIGLACENTRO = sIGLACENTRO;
   }

   public String getNOMECENTRO() {
   return NOMECENTRO.trim();
   }

   public void setNOMECENTRO(String nOMECENTRO) {
   this.NOMECENTRO = nOMECENTRO;
   }

   
}
