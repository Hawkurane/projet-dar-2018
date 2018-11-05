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
            var html_to_insert = `
            <div class="row" style="background: white; margin-bot:10px;">
                <div class="col-sm card">
                    <div class="card-header">
                        Math n° $element.matchId, Day n° $element.matchDay, $element.time ($element.status)
                    </div>
                    <div class="card-body">
                        <p class="card-text">$element.homeTeamName vs $element.awayTeamName</p>
                    </div>
                </div>
            </div>`;

            doculent.getElementById("betscontainer").insertAdjacentHTML('beforeend', html_to_insert);
        });
    }
);