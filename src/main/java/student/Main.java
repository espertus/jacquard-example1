package student;

import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.spertus.jacquard.checkstylegrader.CheckstyleGrader;
import com.spertus.jacquard.common.*;
import com.spertus.jacquard.junittester.JUnitTester;
import com.spertus.jacquard.pmdgrader.PmdGrader;
import com.spertus.jacquard.publisher.GradescopePublisher;
import com.spertus.jacquard.syntaxgrader.SyntaxConditionGrader;

import java.util.*;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Autograder.init();

        // For this assignment, students upload only a single file.
        final Target target = Target.fromClass(FavoritesIterator.class);

        // Create checkstyle grader.
        CheckstyleGrader checkstyleGrader = new CheckstyleGrader(
                "config/checkstyle-rules.xml",
                1.0,
                5.0);

        // Create PMD grader.
        PmdGrader pmdGrader = PmdGrader.createFromRules(
                1.0,
                5.0,
                "category/java/bestpractices.xml",
                "MissingOverride");

        // Create grader to make sure students did not use enhanced for-loops.
        SyntaxConditionGrader forGrader = new SyntaxConditionGrader(
                0,
                "enhanced for-loop",
                10.0,
                node -> (node instanceof ForEachStmt)
        );

        // Create grader to make sure students did not call the hasNext()
        // or next() methods of other classes.
        final List<String> methodNames = List.of("hasNext", "next");
        SyntaxConditionGrader nextGrader = new SyntaxConditionGrader(
                0,
                "iterator method calls",
                20.0,
                node -> {
                    // Check if method call is to next() or hasNext().
                    if (node instanceof MethodCallExpr methodCallExpr &&
                            methodNames.contains(methodCallExpr.getNameAsString())) {
                        // If so, flag if scope is explicit and not "this".
                        return methodCallExpr.getScope().isPresent() &&
                                !(methodCallExpr.getScope().get() instanceof ThisExpr);
                    }
                    return false;
                });

        // Run all graders, collecting results.
        List<Result> results = Grader.gradeAll(
                target,
                checkstyleGrader, pmdGrader, forGrader, nextGrader);

        // Run unit tests, adding on to existing results.
        JUnitTester runner = new JUnitTester(
                // 40 points
                HiddenFavoritesIteratorTest.class,
                // 20 points
                ProvidedFavoritesIteratorTest.class);
        List<Result> junitResults = runner.run();
        results.addAll(junitResults);

        // Display the results.
        new GradescopePublisher().displayResults(results);

        // Explicitly exit (required by Gradle).
        exit(0);
    }
}
