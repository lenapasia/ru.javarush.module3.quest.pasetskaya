<div class="px-1 my-5">
    <div>
        <h4>Statistics:</h4>
    </div>
    <div>
        IP address: <%= request.getRemoteAddr() %>
    </div>
    <div>
        Username: ${sessionScope.user.name}
    </div>
    <div>
        Games count: ${sessionScope.user.playedGamesCount}
    </div>
</div>