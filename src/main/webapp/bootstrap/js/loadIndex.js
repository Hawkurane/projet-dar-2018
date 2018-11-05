$.get(
    "/search",
    {
        matchday: "",
        status: "",
        teamname: "",
        league: "",
        searchType: "bets"
    },
    function(data){
        console.log(data);
    }
);