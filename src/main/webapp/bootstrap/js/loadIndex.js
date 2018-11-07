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
        data.forEach(function(element){
            var color;
            console.log(element);
            if(element.status!=='FINISHED')
                color = 'bg-warning';
            else
                if(element.winner==element.bet)
                    color='bg-success';
                else
                    color='bg-danger';
            var html_to_insert = `
            <div class="row">
                <div class="card">
                    <div class="card-header text-white ${color} mb-3">
                        [${element.league}] Match n°${element.matchId}, Day n°${element.matchDay}, ${element.time} (${element.status})
                    </div>
                    <div class="card-body">
                        <p class="card-text">${element.homeTeamName} ${element.homeTeamg} - ${element.awayTeamg} ${element.awayTeamName}</p>
                    </div>
                </div>
            </div>`;

            document.getElementById("betscontainer").insertAdjacentHTML('beforeend', html_to_insert);
        });
    }
);