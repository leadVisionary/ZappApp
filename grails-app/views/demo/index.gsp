<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>
      <g:message code="live.demo.title" default="Welcome to the Zapp App Live Demo!"/>
    </title>
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
    <g:render template="/demo/cardList" model="['name':'Zapp', 'cards':zappCards]" />
    <div id="userWallet" />
</body>
</html>
