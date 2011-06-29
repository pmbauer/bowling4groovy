package wordwrap

import groovy.transform.Immutable

class Text {
  static def wrapperInstances = { int col -> return new Wrapper(col) }.memoize();

  static String wrap(String input, int maxWidth) {
    return wrapperInstances(maxWidth).wrap(input);
  }
}

@Immutable
static class Wrapper {
  int maxWidth

  String wrap(String input) {
    if (input.length() <= maxWidth)
      return input

    int spaceIdx = input[0..<maxWidth].lastIndexOf(' ')

    if (spaceIdx != -1)
      return breakLine(input, spaceIdx, 1);
    else if (input[maxWidth] == ' ')
      return breakLine(input, maxWidth, 1);
    else
      return breakLine(input, maxWidth);
  }

  String breakLine(String line, int pos, int gap = 0) {
    return line[0..<pos] + '\n' + wrap(line.substring(pos + gap))
  }
}
