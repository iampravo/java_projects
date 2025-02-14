package com.deliveroo.cronexpressionparser.parser.field;

import com.deliveroo.cronexpressionparser.exception.ExpressionValidationException;
import com.deliveroo.cronexpressionparser.utilities.StringUtils;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static com.deliveroo.cronexpressionparser.parser.field.MonthFieldExpressionParser.MONTH;

public class MonthFieldExpressionParserTest extends TestCase {
    public static final String TEST1 = "1,8";
    public static final String TEST2 = "1,,8";
    public static final String TEST3 = "*/8";

    @InjectMocks
    MonthFieldExpressionParser monthFieldExpressionParser;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public void testValidateFieldExpression() {
        try {
            monthFieldExpressionParser.validateFieldExpression(TEST1);
        } catch (ExpressionValidationException ex) {
            fail("Method ValidateFieldExpression failed for input : " + TEST1);
        }
    }

    public void testValidateFieldExpressionWithEx() {
        try {
            monthFieldExpressionParser.validateFieldExpression(TEST2);
            fail("The expected ExpressionValidationException was not occurred.");
        } catch (ExpressionValidationException ignored) {
        }
    }

    public void testParseFieldExpression() throws ExpressionValidationException {
        String output = monthFieldExpressionParser.parseFieldExpression(TEST1);
        Assert.assertEquals(output, MONTH + StringUtils.ARROW + "1 8");
    }

    public void testParseAsteriskValue() {
        String output = monthFieldExpressionParser.parseAsteriskValue();
        Assert.assertNotNull(output);
    }

    public void testParseStepValues() {
        String output = monthFieldExpressionParser.parseStepValues(TEST3);
        Assert.assertNotNull(output);
    }
}