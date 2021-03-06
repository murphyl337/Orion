$(document).ready(function(){
    var game = createGame();
    if(game.currentPlayer.type === 'computer'){
        var move = game.getBestMove(game.board, game.currentPlayer);
        game.currentPlayer.makeMove(game, move);
    }

    game.listen();
});

function createGame(){
    var board = new GameBoard();
    var player1 = new Player("X", "human");
    var player2 = new Player("O", "computer");
    board.initialize();
    return new Game(board, player1, player2);
}

GameBoard.prototype = new Subject();
GameBoard.prototype.constructor = Subject;

GameBoard.prototype.clone = function(){
    var clone = new GameBoard();
    clone.initialize();
    for(var space=0; space<9; space++){
        clone.spaces[space].mark = this.spaces[space].mark;
        clone.spaces[space].position = this.spaces[space].position;
    }
    return clone;
};

function GameBoard(){
    Subject.call(this);
    this.spaces = [];
    var boardSize = 9;
    var self = this;
    var view = new Observer();

    view.Update = function(){
        for(var space=0; space<9; space++){
            var box = document.getElementById(space);
            box.innerHTML = self.spaces[space].mark;
            box.setAttribute("class", "box " + self.spaces[space].color);
        }
    };

    this.addObserver(view);

    this.initialize = function(){
        for(var space=0; space<boardSize; space++){
            this.spaces[space] = new Space("");
            this.spaces[space].position = space;
            this.spaces[space].color = "";
        }
    };

    this.updateBoard = function(player, position, notifyObservers){
        this.spaces[position].mark = player.marker;
        this.spaces[position].color = player.color;
        if(notifyObservers)
           this.notifyObservers();
    };

    this.getAvailableSpaces = function(){
        var spaces = [];
        for(var space=0; space<boardSize; space++){
            if(this.spaces[space].mark === "")
                spaces.push(this.spaces[space]);
        }
        return spaces;
    };

    this.hasOpenSpaces = function(){
        return (this.getAvailableSpaces().length > 0);
    };
}

function Space(mark){
    this.mark = mark;
    this.position;
    this.color;
}

function Player(marker, type){
    this.marker = marker;
    this.type   = type;
    if(marker === "X")
        this.color = "green";
    else
        this.color = "pink";
    var rules = new GameRules();

    this.makeMove = function(game, position){
        if(rules.isValidMove(game.board, position)){
            game.board.updateBoard(this, position, true);
            game.nextTurn();
        }
    };
}

Game.prototype = new Subject();
Game.prototype.constructor = Subject;

function Game(board, player1, player2){
    Subject.call(this);
    this.board   = board;
    this.player1 = player1;
    this.player2 = player2;
    this.currentPlayer = player1;
    var game = this;
    var rules = new GameRules();
    var overlay = new Observer();

    overlay.Update = function(){
        var info = document.getElementById("info");
        info.innerHTML = rules.getState(game.board);
    };

    this.addObserver(overlay);

    this.listen = function(){
        var $box = $(".box");
        var $reset = $(".reset");
        $box.on('click', game.handleClick);
        $reset.on('click', game.reset);
    };

    this.handleClick = function(event){
        if(!rules.isGameOver(board)){
            if(rules.isValidMove(game.board, event.target.id))
                game.currentPlayer.makeMove(game, event.target.id);
        }
    };

    this.reset = function(){
        game.board.initialize();
        game.currentPlayer = game.player1;
        if(game.currentPlayer.type === 'computer')
            game.currentPlayer.makeMove(game, 0);
        game.notifyObservers();
        game.board.notifyObservers();
    };

    this.nextTurn = function(){
        this.currentPlayer = this.getOtherPlayer(this.currentPlayer);

        if(!rules.isGameOver(board)){
            if(this.currentPlayer.type === "computer"){
                var move = game.getBestMove(game.board, game.currentPlayer);
                this.currentPlayer.makeMove(game, move);
            }
        }

        this.notifyObservers();
    };

    this.getOtherPlayer = function(player){
        return (player === this.player1) ? this.player2 : this.player1;
    };


    this.getDefaultBestScore = function(player){
        return (player === this.player1) ? -10000 : 10000;
    };

    this.isBestScore = function(score, bestScore, player){
        var isBestScore = false;
        if(player === this.player1){
            if(score > bestScore) isBestScore = true;
        }
        else if(player === this.player2){
            if(score < bestScore) isBestScore = true;
        }
        return isBestScore;
    };

    this.getBestMove = function(board, player){
        var otherPlayer = this.getOtherPlayer(player);
        var availableSpaces = board.getAvailableSpaces();
        var bestMove;
        var bestScore = this.getDefaultBestScore(player);

        for(var space = 0; space < availableSpaces.length; space++){
            var gameBoardChild = board.clone();
            gameBoardChild.updateBoard(player, availableSpaces[space].position, false);
            var currentScore = this.minimax(gameBoardChild, otherPlayer);
            var isBestScore = this.isBestScore(currentScore, bestScore, player);
            if(isBestScore){
                bestScore = currentScore;
                bestMove = availableSpaces[space].position;
            }
        }
        return bestMove;
    };

    this.minimax = function(board, player){
        if(rules.isWinner(board, this.player1.marker)) return 1;
        else if(rules.isWinner(board, this.player2.marker)) return -1;
        else if(rules.isDraw(board)) return 0;

        var otherPlayer = this.getOtherPlayer(player);
        var availableSpaces = board.getAvailableSpaces();
        var bestScore = this.getDefaultBestScore(player);

        for(var space = 0; space < availableSpaces.length; space++){
            var gameBoardClone = board.clone();
            gameBoardClone.updateBoard(player, availableSpaces[space].position, false);
            var score = this.minimax(gameBoardClone, otherPlayer);
            var isBestScore = this.isBestScore(score, bestScore, player);
            if(isBestScore)
                bestScore = score;
        }
        return bestScore;
    };
}