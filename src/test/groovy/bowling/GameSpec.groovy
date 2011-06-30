package bowling

import spock.lang.Specification

class GameSpec extends Specification {
  def "Game fixtures"() {
    expect:
    score == rolls.playGame(description).score

    where:
    description    | rolls                              | score
    "gutter game"  | roll.many(20,0)                    | 0
    "all ones"     | roll.many(20,1)                    | 20
    "one spare"    | roll.spare().once(3).many(17,0)    | 16
    "one strike"   | roll.strike().once(3,4).many(16,0) | 24
    "perfect game" | roll.many(12,10)                   | 300
  }

  RollCollector getRoll() {
    new RollCollector()
  }
}

class RollCollector {
  private def rolls = []
  private Game game = new Game()

  RollCollector once(int... rolls) {
    this.rolls.addAll(rolls)
    return this
  }

  RollCollector once(List rolls) { once(rolls as int[]) }
  RollCollector strike() { once(10) }
  RollCollector spare() { once(5, 5) }
  RollCollector many(int n, int pins) { once((0..<n).collect {pins}) }

  Game playGame(String description) { game.roll(rolls as int[]) }
}
