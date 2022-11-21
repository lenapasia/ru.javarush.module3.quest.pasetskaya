<div class="container col-xxl-8 px-4 py-5">
    <div class="row flex-lg-row-reverse align-items-center g-5 py-5">
        <div>
            <p>Statistics:</p>
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
</div>