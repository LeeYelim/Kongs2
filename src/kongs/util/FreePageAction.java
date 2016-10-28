package kongs.util;

public class FreePageAction {
	/**
	 * 페이지 분할에 필요한 Field 선언
	 * */
   public static int pageSize = 10  ;//한페이지에 보여질 게시물 수
   public static int blockCount = 3 ;//한페이지당 보여질 []의 개수
   public static int pageCount = 1; //전체페이지 수
   public static int pageNum = 1;//현재페이지번호
   public static int startPage = 1;// 한블록에서 보여질 []의 시작번호
   
   public static String keyField;//검색필드(대상)
   public static String keyWord;//검색단어
   
   /**
    * 페지분할의 결과를 String으로 만들어 리턴해 줄 메소드 작성
    * ex) [이전]   [1][2][3] .. [다음]
    * 
    * */
   public static String pageNumber(){
	   String str="";
	   
	   //1.화면의 시작페이지를 구한다(몇번부터 출력 할지를 정함 [번호] ) 
	    int temp =  (pageNum-1) % blockCount; 
	    startPage = pageNum - temp; 
	   
	   //2. [이전] 만들기
	    if( (startPage - blockCount) >  0 ){
	       str="<a href='front?command=free&pageNum="+(startPage-1)+"&keyField="+keyField+"&keyWord="+keyWord+"'>◀이전 </a>&nbsp;&nbsp;" ;
	    }
	    
	   //3. 페이지번호 나열하기 
	    for(int i = startPage ; i < (startPage+blockCount) ; i++){
	    	if(i==pageNum)
	           str+="<font color='red'>["+i +"]</font>&nbsp;";
	    	else
	    		str+="<a href='front?command=free&pageNum="+i+"&keyField="+keyField+"&keyWord="+keyWord+"'>["+i +"]</a>&nbsp;";	
	    	
	        if(i >= pageCount) break;
	    }
	    
	    
	   //4.[다음]  만들기

	    str+="&nbsp;&nbsp;";
	    
	    if( (startPage + blockCount) <= pageCount ){
	       str+="<a href='front?command=free&pageNum="+(startPage+blockCount)+"&keyField="+keyField+"&keyWord="+keyWord+"'> ▶다음 </a>" ;
	    }   
	    
	   
	   return str;
	   
   }
   

}




