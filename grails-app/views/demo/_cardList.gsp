<section>
  <h1>Cards ${name} has</h1>
  <g:if test="${!cards}">
    <p>${name} has no cards</p>
  </g:if>
  <g:else>
    <ul>
    <g:each in="${cards}" var="card">
      <li><span>${card.name} - ${card.phoneNumber}</span></li>
    </g:each>
    </ul>
  </g:else>
</section>
    