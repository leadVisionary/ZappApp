<!doctype html>
<html>
  <head>
    <meta name="layout" content="main"/>
    <link rel="stylesheet" href="${resource(dir:'css', file:'zapp.css')}" />
    <r:require modules="jquery-ui" />
  </head>
  <body>
    <div id="container" class="twoColumn">
      <div id="phoneContainer">
        <img src="${resource(dir:'images', file:'phone.png')}" 
             alt="phone" />
      </div>
      <div id="blurb">
        <img src="${resource(dir:'images', file:'heading.png')}"
             alt="Locate and Exchange Contact Information with just the click of a button" />
        <button id="try">
          <g:link controller="demo">Try it</g:link>
        </button>
      </div>
    </div>
  <r:script>
    jQuery(document).ready(function(){ jQuery("#try").addClass("ui-corner-all");});
  </r:script>
  </body>
</html>
