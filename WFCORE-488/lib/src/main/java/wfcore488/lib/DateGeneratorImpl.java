/*
 * Copyright (C) 2014 Computer Science Corporation
 * All rights reserved.
 *
 */
package wfcore488.lib;

import java.util.Date;

/**
 * @author arcivanov
 */
public class DateGeneratorImpl implements DateGenerator
{

    /* (non-Javadoc)
     * @see wfcore488.lib.DateGenerator#getDate()
     */
    @Override
    public String getDate()
    {
        return new Date().toString();
    }
}
