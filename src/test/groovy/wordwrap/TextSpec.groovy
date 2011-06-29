package wordwrap

import spock.lang.Specification

class TextSpec extends Specification {
  def "Wrap fixtures"() {
    expect:
    output == Text.wrap(input, columnWidth)

    where:
    columnWidth | input                  | output
    3           | ""                     | ""
    5           | "word"                 | "word"
    2           | "word"                 | "wo\nrd"
    5           | "supercalifraglistic"  | "super\ncalif\nragli\nstic"
    5           | "word word"            | "word\nword"
    5           | "word word word"       | "word\nword\nword"
    6           | "word word word"       | "word\nword\nword"
    3           | "word word"            | "wor\nd\nwor\nd"
    4           | "word word word"       | "word\nword\nword"
  }
}
