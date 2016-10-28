package kongs.util;

public class FindPageAction {
	/**
	 * ������ ���ҿ� �ʿ��� Field ����
	 * */
   public static int pageSize = 10  ;//���������� ������ �Խù� ��
   public static int blockCount = 3 ;//���������� ������ []�� ����
   public static int pageCount = 1; //��ü������ ��
   public static int pageNum = 1;//������������ȣ
   public static int startPage = 1;// �Ѻ�Ͽ��� ������ []�� ���۹�ȣ
   
   public static String keyField;//�˻��ʵ�(���)
   public static String keyWord;//�˻��ܾ�
   
   /**
    * ���������� ����� String���� ����� ������ �� �޼ҵ� �ۼ�
    * ex) [����]   [1][2][3] .. [����]
    * 
    * */
   public static String pageNumber(){
	   String str="";
	   
	   //1.ȭ���� ������������ ���Ѵ�(������� ��� ������ ���� [��ȣ] ) 
	    int temp =  (pageNum-1) % blockCount; 
	    startPage = pageNum - temp; 
	   
	   //2. [����] �����
	    
	    if( (startPage - blockCount) >  0 ){
	       str="<a href='findBoard.jsp?pageNum="+(startPage-1)+"&keyField="+keyField+"&keyWord="+keyWord+"'>������ </a>&nbsp;&nbsp;" ;
	    }
	    
	   //3. ��������ȣ �����ϱ� 
	    System.out.println("pageCount = "+pageCount);
	    for(int i = startPage ; i < (startPage+blockCount) ; i++){
	    	if(i==pageNum)
	           str+="<font color='red'>["+i +"]</font>&nbsp;";
	    	else
	    		str+="<a href='findBoard.jsp?pageNum="+i+"&keyField="+keyField+"&keyWord="+keyWord+"'>["+i +"]</a>&nbsp;";	
	    	
	        if(i >= pageCount) break;
	    }
	    
	    
	   //4.[����]  �����

	    str+="&nbsp;&nbsp;";
	    
	    if( (startPage + blockCount) <= pageCount ){
	       str+="<a href='findBoard.jsp?pageNum="+(startPage+blockCount)+"&keyField="+keyField+"&keyWord="+keyWord+"'> ������ </a>" ;
	    }   
	    
	    System.out.println("str = "+ str);
	   
	   return str;
	   
   }
   

}




