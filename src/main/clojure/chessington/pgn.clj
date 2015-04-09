(ns chessington.pgn
  (:import [com.codethesis.pgnparse PGNSource Color]))

(defn ->PGN [path]
  (with-open [file (clojure.java.io/input-stream path)]
    (PGNSource. file)))

(defn- move->clj [pgn-move]
  {:move (.getMove pgn-move)
   :full-move (.getFullMove pgn-move)
   :from-square (.getFromSquare pgn-move)
   :to-square (.getToSquare pgn-move)
   :piece (.getPiece pgn-move)
   :color (if (= Color/black (.getColor pgn-move)) :black :wite)
   :comment (.getComment pgn-move)
   :check? (.isChecked pgn-move)
   :check-mate? (.isCheckMated pgn-move)
   :captured? (.isCaptured pgn-move)
   :promoted? (.isPromoted pgn-move)
   :promotion (.getPromotion pgn-move)
   :end-game-mark (.getEndGameMark pgn-move)
   :end-game? (.isEndGameMarked pgn-move)
   :king-side-castle? (.isKingSideCastle pgn-move)
   :queen-side-castle? (.isQueenSideCastle pgn-move)
   :enpassant? (.isEnpassant pgn-move)
   :enpassant-piece-square (.getEnpassantPieceSquare pgn-move)})

(defn games [pgn]
  (let [all (.listGames pgn)]
    (map #(let [moves (.getMoves %)]
            {:moves (map move->clj moves)})
         all)))
