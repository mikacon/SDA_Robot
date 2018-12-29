/*
 *	  Copyright (C) 2016  John H. Gaby
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, version 3 of the License.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *    
 *    Contact: robotics@gabysoft.com
 */

package robotCore;

import java.io.PrintWriter;

import robotCore.AnalogEncoders;

/**
 * 
 * @author John Gaby
 * 
 * @brief The AnalogEncoder class handles up to two potentiometer based analog encoders
 * 
 * This class is capable of reading two potentiometer based encoders which are attached
 * to the analog inputs of the Arduino microcontroller.
 * 
 * Each encoder consists of two potentiometers and requires two analog inputs.<br>  
 * 	Encoder 0 should be attached to analog pins 0 and 1.<br>  
 * 	Encoder 1 should be attached to analog pins 2 and 3.
 * 
 * NOTE: The AnalogEncoder should not be instantiated before the robot is initialized
 * (i.e. do not create an instance of this class in the constructors of your classes
 * unless you do not instantiate those classes until after the robot is initialize
 * with a call to Robot.robotInit)
 *
 */
public class AnalogEncoder implements PIDSource, SpeedEncoder, Encoder
{
	private int m_zero	   = 0;
	private boolean m_reverse = false;
	private AnalogEncoders.AnalogEncoderData m_data;
	private long m_logTime	= 0;
	private PrintWriter m_log = null;
	
	private void InitEncoder(int port, boolean reverse)	
	{
		m_data	= AnalogEncoders.GetInstance().GetData(port);
		m_reverse = reverse;
	}
	
	/** 
	 *  @param encoderNo - Specifies the analog encoder number (i.e. 0 or 1)
	 *  @param reverse - If <strong>true</strong>, negate the returned position.
	 */
	public AnalogEncoder(int encoderNo, boolean reverse)
	{
		InitEncoder(encoderNo, reverse);
	}
	
	/** 
	 *  @param encoderNo - Specifies the encoder number (i.e. 0 or 1)
	 */
	public AnalogEncoder(int encoderNo)
	{
		InitEncoder(encoderNo, false);
	}
	
	private AnalogEncoder(AnalogEncoder src)
	{
		m_data		= src.m_data;
		m_reverse	= src.m_reverse;
		m_zero		= src.m_zero;
	}
	
	/**
	 * 
	 * @return Return a copy of the encoder
	 * 
	 * This returns a new instance of the encoder.  This instance
	 * refers to the same physical device as the original, but
	 * has it's own zero setting independent of the original.
	 */
	public AnalogEncoder Clone()
	{
		return(new AnalogEncoder(this));
	}
	
	/**
	 * Gets the current encoder value (calls get())
	 */
	@Override
	public double pidGet() 
	{
		return get();
	}
	
	/**
	 * Gets the current encoder value
	 */
	@Override
	public int get()
	{
		int	pos = m_data.GetPosition() - m_zero;
		
		return(m_reverse ? -pos : pos);
	}
	
	/**
	 * Gets the current rate (i.e. speed)
	 */
	@Override
	public int getRate()
	{
		int	rate	= m_data.GetRate();
		
		if (m_reverse)
		{
			rate	= -rate;
		}
		
		if (m_log != null)
		{
//			System.out.println(System.currentTimeMillis() - m_logTime + "," + rate);
			m_log.println(System.currentTimeMillis() - m_logTime + "," + get() + "," + rate);
			m_log.flush();
		}
		
		return(rate);
	}
	
	/**
	 * Resets the encoder to zero
	 */
	public void reset()
	{
		m_zero	= m_data.GetPosition();
//		System.out.println("reset: zero = " + m_zero);
	}
	
/*	public void Log(String file)
	{
		if (m_log != null)
		{
			m_log.close();
		}
		
		try 
		{
			m_log	= new PrintWriter(file);
			m_log.println("time,pos,speed");
			m_logTime = System.currentTimeMillis();
		} 
		catch (FileNotFoundException e) 
		{
			m_log	= null;
			e.printStackTrace();
		}
	}
	
	public void LogEnd()
	{
		if (m_log != null)
		{
			m_log.close();
			m_log	= null;
		}
	}*/
}
