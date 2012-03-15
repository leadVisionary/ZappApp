<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>
      <g:message code="live.demo.title" default="Welcome to the Zapp App Live Demo!"/>
    </title>
    <r:require modules="jquery-ui" />
  </head>
  <body>
    <header class="leftColumn">
      <blockquote class="example-obtuse">
            <p><g:message code="live.demo.instructions" /></p>
      </blockquote>  
      <img src="${resource(dir:'images', file:'Zapp.png')}"
               alt="Say hello to Zapp Appanigan!"/>
    </header>
    <div class="rightColumn">
      <g:render template="/demo/input" />
    </div>
    <link rel="stylesheet" 
          type="text/css" 
          href="http://leandrovieira.com/projects/jquery/lightbox/css/jquery.lightbox-0.5.css" />
    <script type="text/javascript" 
    src="http://leandrovieira.com/projects/jquery/lightbox/js/jquery.lightbox-0.5.pack.js"></script>
</body>
</html>
