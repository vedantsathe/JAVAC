<%@ page import="java.util.*" %>

<%
if(request.getParameter("s1")!=null)
  {
	Integer num,a,i,sum = 0;
	
	num = Integer.parseInt(request.getParameter("num"));
	a = num;

	for(i=1;i<a;i++)
	{
		if(a%i==0)
		{
			sum=sum + i;
		}
	}
	if(sum==a)
	{
		out.println(+num+ "is a perfect number");
	}
	else
	{
		out.println(+num+ "is not a perfect number");
	}
  }	
%>