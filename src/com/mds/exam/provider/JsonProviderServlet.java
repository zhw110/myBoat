package com.mds.exam.provider;

import com.alibaba.fastjson.JSON;
import com.mds.exam.provider.common.PageQuery;
import com.mds.exam.provider.common.Result;
import com.mds.exam.provider.dataobject.DataDO;
import com.mds.exam.provider.manager.Managers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class JsonProviderServlet
 */
@WebServlet("/getUserData.do") //Annotation Script easy to call later
public class JsonProviderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public JsonProviderServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        // query is for the filter and sort
        PageQuery<DataDO> query = new PageQuery<DataDO>();// PageQuery<DataDO> is a object of the DataDO.
        
        //filter by name
        DataDO q = new DataDO();
        q.setVesselname(request.getParameter("Vesselname"));// get the name value from html and set the DAO
        query.setQueryObject(q);//set q as to the query
       
        //sort by sequence
        query.setSort(request.getParameter("sort") == null ? "asc" : request.getParameter("sort"));//get the sort from HTML.default is asc
        
       //// get the page from the front part
//        String pagestr = request.getParameter("page"); // set the page of of html, to make it suable for website
//        query.setCurrentPage(pagestr == null ? 1 : Integer.parseInt(pagestr));// set the page number of the HTML

        //transfor the conditions of the query fucntions
        Result<PageQuery<DataDO>> result = Managers.getDataManager().query(query); // set the parameters the query function 
        
        //display in HTML with JSON
        out.println(JSON.toJSONString(result));
        
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    } 
}











