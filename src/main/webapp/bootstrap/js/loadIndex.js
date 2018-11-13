$.get(
    "/search",
    {
        matchday: "",
        status: "",
        teamname: "",
        league: "",
        searchType: "bets",
        won: ""
    },
    function(data){
        var html_to_insert='';
        data.forEach(function(element){
            var color;
            console.log(element);
            if(element.status=='FINISHED')
                if(element.winner==element.bet)
                    color='bg-success';
                else
                    color='bg-danger';

            html_to_insert += `
            <div class="row">
                <div class="card" style="width: 100%">
                    <div class="card-header text-white ${color} mb-3">
                        [${element.league}] Match n°${element.matchId}, Day n°${element.matchDay}, ${element.time} (${element.status})
                    </div>
                    <div class="card-body">
                        <div class="media">
                            <img class="align-self-center mr-3 img-thumbnail rounded-circle scale-down" src="${element.homeTeamLogoUrl}" alt="Home team Logo" style="width:64px;height=64px">
                            <div class="media-body">
                                <p class="card-text">${element.homeTeamName} ${element.homeTeamg} - ${element.awayTeamg} ${element.awayTeamName}</p>
                            </div>
                            <img class="align-self-center ml-3 img-thumbnail rounded-circle scale-down" src="${element.awayTeamLogoUrl}" alt="Away team Logo" style="width:64px;height=64px">

                        </div>
                    </div>
                </div>
            </div>`;

        });
        document.getElementById("betscontainer").insertAdjacentHTML('beforeend', html_to_insert);

    }
);