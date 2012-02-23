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
    <p>
      <g:message code="live.demo.instructions" />
    </p>
    <g:form action="broadcast">
      <label for="email">
        <g:message code="live.demo.email.label" default="Email address" />
      </label>
      <input type="email" name="email" />
      <label for="phoneNumber">
        <g:message code="live.demo.phoneNumber.label" default="Phone Number" />
      </label>
      <input type="tel" name="phoneNumber" />
      <g:submitButton name="Go" value="Exchange Cards!" />
    </g:form>
  </body>
</html>
