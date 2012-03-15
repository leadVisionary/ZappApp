<g:form action="broadcast">
  <label for="name">
    <g:message code="live.demo.name.label" default="Name" />
    <g:textField name="name" />
  </label>
  <label for="email">
    <g:message code="live.demo.email.label" default="Email address" />
    <input type="email" name="email" />
  </label>
  <label for="phoneNumber">
    <g:message code="live.demo.phoneNumber.label" default="Phone Number" />
    <input type="tel" name="phoneNumber" />
  </label>
  <g:submitButton name="Go" value="Exchange Cards!" />
</g:form>