/*******************************************************************************
 * Copyright (c) 2016-2018 Martin Weber.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      Martin Weber - Initial implementation
 *******************************************************************************/
package de.marw.cmake.cdt.internal.lsp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;

import de.marw.cmake.cdt.internal.lsp.ParserDetection;

/**
 * @author Martin Weber
 */
public class ParserDetectionTest {

  /**
   * Test method for
   * {@link de.marw.cmake.cdt.internal.lsp.ParserDetection#determineDetector(String, String,boolean)}
   * .
   */
  @Test
  public void testDetermineParserForCommandline_clang() {
    ParserDetection.ParserDetectionResult result = ParserDetection.determineDetector("/usr/bin/clang -C blah.c", null,
        true);
    assertNotNull(result);
    // verify that we got a C parser
    assertEquals("C", "org.eclipse.cdt.core.gcc", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));
  }

  @Test
  public void testDetermineParserForCommandline_clangplusplus() {
    ParserDetection.ParserDetectionResult result = ParserDetection.determineDetector("/usr/bin/clang++ -C blah.c", null,
        true);
    assertNotNull(result);
    // verify that we got a C++ parser
    assertEquals("C++", "org.eclipse.cdt.core.g++", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));
  }

  @Test
  public void testDetermineParserForCommandline_clangplusplus_basename() {
    ParserDetection.ParserDetectionResult result = ParserDetection.determineDetector("clang++ -C blah.c", null, false);
    assertNotNull(result);
    // verify that we got a C++ parser
    assertEquals("C++", "org.eclipse.cdt.core.g++", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));
  }

  /**
   * Test method for
   * {@link de.marw.cmake.cdt.internal.lsp.ParserDetection#determineDetector(String, String,boolean)}
   * .
   * <a href="https://wiki.osdev.org/Target_Triplet"/>
   */
  @Test
  public void testDetermineParserForCommandline_cross() {
    ParserDetection.ParserDetectionResult result = ParserDetection.determineDetector("/usr/bin/arm-none-eabi-gcc -C blah.c", null,
        true);
    assertNotNull(result);
    // verify that we got a C parser
    assertEquals("C", "org.eclipse.cdt.core.gcc", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));

    result = ParserDetection.determineDetector("/usr/bin/arm-none-eabi-gcc.exe -C blah.c", null,
        true);
    assertNotNull(result);
    // verify that we got a C parser
    assertEquals("C", "org.eclipse.cdt.core.gcc", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));
  }

  @Test
  public void testDetermineParserForCommandline_cross_withVersion() {
    final String versionSuffixRegex = "-?\\d+(\\.\\d+)*";

    ParserDetection.ParserDetectionResult result = ParserDetection.determineDetector("/usr/bin/arm-none-eabi-gcc-9.2.0 -C blah.c", versionSuffixRegex,
        true);
    assertNotNull(result);
    // verify that we got a C parser
    assertEquals("C", "org.eclipse.cdt.core.gcc", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));

    result = ParserDetection.determineDetector("/usr/bin/arm-none-eabi-gcc-9.2.0.exe -C blah.c", versionSuffixRegex,
        true);
    assertNotNull(result);
    // verify that we got a C parser
    assertEquals("C", "org.eclipse.cdt.core.gcc", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));
  }

  /**
   * <a href="https://wiki.osdev.org/Target_Triplet"/>
   */
  @Test
  public void testDetermineParserForCommandline_crossplusplus() {
    ParserDetection.ParserDetectionResult result = ParserDetection.determineDetector("/usr/bin/arm-none-eabi-g++ -C blah.c", null,
        true);
    assertNotNull(result);
    // verify that we got a C++ parser
    assertEquals("C++", "org.eclipse.cdt.core.g++", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));

    result = ParserDetection.determineDetector("/usr/bin/arm-none-eabi-g++.exe -C blah.c", null,
        true);
    assertNotNull(result);
    // verify that we got a C++ parser
    assertEquals("C++", "org.eclipse.cdt.core.g++", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));
  }

  /**
   * <a href="https://wiki.osdev.org/Target_Triplet"/>
   */
  @Test
  public void testDetermineParserForCommandline_crossplusplus_withVersion() {
    final String versionSuffixRegex = "-?\\d+(\\.\\d+)*";

    ParserDetection.ParserDetectionResult result = ParserDetection.determineDetector("/usr/bin/arm-none-eabi-g++-9.2.0 -C blah.c", versionSuffixRegex,
        true);
    assertNotNull(result);
    // verify that we got a C++ parser
    assertEquals("C++", "org.eclipse.cdt.core.g++", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));

    result = ParserDetection.determineDetector("/usr/bin/arm-none-eabi-g++-9.2.0.exe -C blah.c", versionSuffixRegex,
        true);
    assertNotNull(result);
    // verify that we got a C++ parser
    assertEquals("C++", "org.eclipse.cdt.core.g++", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));
  }

  /**
   * <a href="https://wiki.osdev.org/Target_Triplet"/>
   */
  @Test
  public void testDetermineParserForCommandline_crossplusplus_basename() {
    ParserDetection.ParserDetectionResult result = ParserDetection.determineDetector("arm-none-eabi-g++ -C blah.c", null, false);
    assertNotNull(result);
    // verify that we got a C++ parser
    assertEquals("C++", "org.eclipse.cdt.core.g++", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));

    result = ParserDetection.determineDetector("arm-none-eabi-g++.exe -C blah.c", null, false);
    assertNotNull(result);
    // verify that we got a C++ parser
    assertEquals("C++", "org.eclipse.cdt.core.g++", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));}

  @Test
  @Ignore("Requires NFTS to run")
  public void testDetermineParserForCommandline_MsdosShortNames() {
    ParserDetection.ParserDetectionResult result = ParserDetection
        .determineDetector("C:\\PROGRA2\\Atmel\\AVR8-G1\\bin\\AVR-G_~1.EXE -C blah.c", null, true);
    assertNotNull(result);
    // verify that we got a C parser
    assertEquals("C", "org.eclipse.cdt.core.gcc", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));
  }

  @Test
  public void testDetermineParserForCommandline_withVersion() {
    final String versionSuffixRegex = "-?\\d+(\\.\\d+)*";

    ParserDetection.ParserDetectionResult result = ParserDetection.determineDetector("/usr/bin/cc-4.1 -C blah.c",
        versionSuffixRegex, false);
    assertNotNull(result);
    // verify that we got a C parser
    assertEquals("org.eclipse.cdt.core.gcc", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));

    result = ParserDetection.determineDetector("/usr/bin/cc-4.1.exe -C blah.c", versionSuffixRegex, true);
    assertNotNull(result);
    // verify that we got a C parser
    assertEquals("org.eclipse.cdt.core.gcc", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));

    result = ParserDetection.determineDetector("/usr/bin/c++-4.1 -C blah.c",
        versionSuffixRegex, false);
    assertNotNull(result);
    // verify that we got a C++ parser
    assertEquals("org.eclipse.cdt.core.g++", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));

    result = ParserDetection.determineDetector("/usr/bin/c++-4.1.exe -C blah.c", versionSuffixRegex, true);
    assertNotNull(result);
    // verify that we got a C++ parser
    assertEquals("org.eclipse.cdt.core.g++", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));

    // clang for issue #43
    result = ParserDetection.determineDetector("/usr/local/bin/clang++40 -C blah.c", versionSuffixRegex, false);
    assertNotNull(result);
    assertEquals("org.eclipse.cdt.core.g++", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));
    result = ParserDetection.determineDetector("/usr/local/bin/clang++40 -C blah.c", "40", false);
//    result = ParserDetection.determineDetector("/usr/local/bin/clang++40 -I/home/me/workspace/first/test/../utility -I/home/me/workspace/first/test/../include -I/home/me/workspace/first/test -g -std=c++1y -stdlib=libc++ -include-pch /home/me/workspace/first/build/Debug/test/catch.hpp.pch -include-pch /home/me/workspace/first/build/Debug/test/pch.hpp.pch -o CMakeFiles/first_test.test.dir/__/utility/fun.cpp.o -c /home/me/workspace/first/utility/fun.cpp",
//        "40", false);
    assertNotNull(result);
    assertEquals("org.eclipse.cdt.core.g++", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("c"));

    result = ParserDetection.determineDetector(
        "/apps/tools/cent_os72/binlinks/g++-7.1 " + "-I/apps/tools/cent_os72/thirdparty/boost/boost_1_64_0/include "
            + "-I/home/XXX/repositories/bepa/common/include -g -Wall "
            + "-c /home/XXX/repositories/bepa/common/settings/src/settings.cpp",
        versionSuffixRegex, true);
    assertNotNull(result);
    // verify that we got a C++ parser
    assertEquals("org.eclipse.cdt.core.g++", result.getDetectorWithMethod().getToolDetectionParticipant().getParser().getLanguageId("cpp"));
  }

  @Test
  public void testDetermineParserForCommandline_quoted() {
    String more = " -DFoo=bar \"quoted\" 'quoted' -C blah.c";

    String name = "/us r/bi n/cc";
    ParserDetection.ParserDetectionResult result = ParserDetection.determineDetector(
        "\"" + name +"\"" +more, null, false);
    assertNotNull(result);
    assertEquals(name, result.getCommandLine().getCommand());

    name = "C;/us r/bi n/cc";
    result = ParserDetection.determineDetector(
        "\"" + name +"\"" +more, null, false);
    assertNotNull(result);
    assertEquals(name, result.getCommandLine().getCommand());

    name = "C;\\us r\\bi n\\cc";
    result = ParserDetection.determineDetector(
        "\"" + name +"\"" +more, null, true);
    assertNotNull(result);
    assertEquals(name, result.getCommandLine().getCommand());

    name = "C;\\us r\\bi n\\cc.exe";
    result = ParserDetection.determineDetector(
        "\"" + name +"\"" +more, null, true);
    assertNotNull(result);
    assertEquals(name, result.getCommandLine().getCommand());
  }

  /** Tests whether tool detection regex is too greedy, */
  @Test
  public void testDetermineParserForCommandline_greedyness() {
    String more = " -DFoo=bar -I /opt/mingw53_32/mkspecs/win32-c++ -C blah.c";

    String name = "/usr/bin/c++";
    ParserDetection.ParserDetectionResult result = ParserDetection.determineDetector(name + more, null, false);
    assertNotNull(result);
    assertEquals(name, result.getCommandLine().getCommand());

    result = ParserDetection.determineDetector("\"" + name + "\"" + more, null, false);
    assertNotNull(result);
    assertEquals(name, result.getCommandLine().getCommand());

    // with filename extension
    name += ".exe";
    result = ParserDetection.determineDetector(name + more, null, true);
    assertNotNull(result);
    assertEquals(name, result.getCommandLine().getCommand());

    result = ParserDetection.determineDetector("\"" + name + "\"" + more, null, true);
    assertNotNull(result);
    assertEquals(name, result.getCommandLine().getCommand());
  }
}
