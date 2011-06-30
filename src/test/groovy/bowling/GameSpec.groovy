package bowling

import spock.lang.Specification

class GameSpec extends Specification {
  def "Game fixtures"() {
    expect:
    score == rolls.playGame(description).score

    where:
    description    | rolls                             | score
    "gutter game"  | roll.many(20,0)                   | 0
    "all ones"     | roll.many(20,1)                   | 20
    "one spare"    | roll.spare.three.many(17,0)       | 16
    "one strike"   | roll.strike.three.four.many(16,0) | 24
    "perfect game" | roll.many(12,10)                  | 300
  }

  RollCollector getRoll() {
    new RollCollector()
  }

}

class RollCollector {
  static final numbers = [zero:0, one:1, two:2, three:3, four:4, five:5, six:6, seven:7, eight:8, nine:9, ten:10]
  private def _rolls = []
  private Game game = new Game()

  RollCollector once(int... rolls) {
    _rolls.addAll(rolls)
    return this
  }

  RollCollector getStrike() { once(10) }
  RollCollector getSpare() { once(5, 5) }

  RollCollector many(int n, int pins) {
    once((0..<n).collect {pins} as int[])
  }

  def propertyMissing(String name) {
    numbers[name] ? once(numbers[name]) : this
  }

  Game playGame(String description) {
    game.roll(_rolls as int[])
    return game
  }
}
