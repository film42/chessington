(ns chessington.pgn-test
  (:require [clojure.test :refer :all]
            [chessington.pgn :refer :all]))

(def fixture
  (->PGN "data/mackenzie.pgn"))

(deftest can-load-a-pgn-file
  (testing "loads all games from pgn file"
    (let [all (games fixture)]
      (is (= 198 (count all)))))

  (testing "can load the last move"
    (let [first-game (first (games fixture))
          last-move (last (:moves first-game))]

      (is (= {:full-move "1-0",
              :end-game-mark "1-0",
              :enpassant-piece-square nil,
              :color :black,
              :from-square nil,
              :promoted? false,
              :piece "P",
              :king-side-castle? false,
              :move "1-0",
              :end-game? true,
              :queen-side-castle? false,
              :comment "",
              :captured? false,
              :check-mate? false,
              :to-square nil,
              :enpassant? false,
              :promotion nil,
              :check? false} last-move)))))
