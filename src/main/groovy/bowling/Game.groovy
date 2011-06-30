package bowling

class Game {
  private def rolls = new int[21]
  private int rollIdx = 0

  def roll(int pins) {
    rolls[rollIdx++] = pins
  }

  def roll(int... rolls) {
    rolls.each { roll(it) }
  }

  def getScore() {
    int sum = 0
    int frameIdx = 0

    (0..<10).each {
      if (isStrike(frameIdx)) {
        sum += 10 + strikeBonus(frameIdx++)
      }
      else if (isSpare(frameIdx)) {
        sum += 10 + spareBonus(frameIdx)
        frameIdx += 2
      } else {
        sum += frameScore(frameIdx)
        frameIdx += 2
      }
    }

    return sum
  }

  private int frameScore(int frameIdx) {
    return rolls[frameIdx] + rolls[frameIdx + 1]
  }

  private int strikeBonus(int frameIdx) {
    return rolls[frameIdx + 1] + rolls[frameIdx + 2]
  }

  private int spareBonus(int frameIdx) {
    return rolls[frameIdx + 2]
  }

  private boolean isStrike(int frameIdx) {
    return rolls[frameIdx] == 10
  }

  private boolean isSpare(int frameIdx) {
    return rolls[frameIdx] + rolls[frameIdx + 1] == 10
  }
}
