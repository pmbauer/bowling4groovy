package bowling

import spock.lang.Specification

class GameSpec extends Specification {
  def game = new Game();

  def "test gutter game"() {
    when:
    rollMany(20, 0)

    then:
    0 == game.score
  }

  def "test all ones"() {
    when:
    rollMany(20, 1)

    then:
    20 == game.score
  }

  def "test one spare"() {
    when:
    rollSpare()
    game.roll(3)
    rollMany(17, 0)

    then:
    16 == game.score
  }

  def "test one strike"() {
    when:
    rollStrike()
    game.roll(3)
    game.roll(4)
    rollMany(16,0)

    then:
    24 == game.score
  }

  def "test perfect game"() {
    when:
    rollMany(12,10)

    then:
    300 == game.score
  }

  private rollMany(int n, int pins) {
    (0..<n).each { game.roll(pins) }
  }

  private rollStrike() {
    return game.roll(10)
  }

  private rollSpare() {
    2.times { game.roll(5) }
  }
}

