<g:form action="live">
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
  <g:submitToRemote update="zapCard" 
                    url="[action:'live', controller:'demo']"
                    value="Exchange Cards!" />
</g:form>
<div id="zapCard" />
<r:script>
    jQuery(document).ready(function(){ 
      jQuery("#go").button();
    });
</r:script>