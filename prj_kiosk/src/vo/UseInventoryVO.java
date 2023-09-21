package vo;

public class UseInventoryVO {
   private String pdName;
   private String pdCode;
   private String iCode;
   private String iName;
   private int useQuantity;
   private int totalQuantity;
   
   public UseInventoryVO() {
      
   }

   public UseInventoryVO(String pdName, String pdCode, String iCode, String iName, int useQuantity,
         int totalQuantity) {
      super();
      this.pdName = pdName;
      this.pdCode = pdCode;
      this.iCode = iCode;
      this.iName = iName;
      this.useQuantity = useQuantity;
      this.totalQuantity = totalQuantity;
   }

   public String getPdName() {
      return pdName;
   }

   public String getPdCode() {
      return pdCode;
   }

   public String getiCode() {
      return iCode;
   }

   public String getiName() {
      return iName;
   }

   public int getUseQuantity() {
      return useQuantity;
   }

   public int getTotalQuantity() {
      return totalQuantity;
   }

   public void setPdName(String pdName) {
      this.pdName = pdName;
   }

   public void setPdCode(String pdCode) {
      this.pdCode = pdCode;
   }

   public void setiCode(String iCode) {
      this.iCode = iCode;
   }

   public void setiName(String iName) {
      this.iName = iName;
   }

   public void setUseQuantity(int useQuantity) {
      this.useQuantity = useQuantity;
   }

   public void setTotalQuantity(int totalQuantity) {
      this.totalQuantity = totalQuantity;
   }

   @Override
   public String toString() {
      return "UseInventoryVO [pdName=" + pdName + ", pdCode=" + pdCode + ", iCode=" + iCode + ", iName=" + iName
            + ", useQuantity=" + useQuantity + ", totalQuantity=" + totalQuantity + "]";
   }
   
   
}