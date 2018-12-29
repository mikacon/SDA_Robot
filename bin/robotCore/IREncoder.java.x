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

/**
 * 
 * @author John Gaby
 * 
 * @brief The IREncoder class controls one simple infrared encoder
 * 
 *   The infrared encoder consists of an infrared LED, and sensor that is interrupted
 *   by a spinning slotted disk.  By measuring the time between slots, the speed of
 *   the spinning disk can be determined. 
 *   
 *   The relative position can also be computed by counting
 *   the number of slots that have passed the detector.  Note, however
 *   that this encoder is <strong>not</strong> a quadrature encoder and can, therefore,
 *   not determine the direction of the motor.
 *   
 *   The output from the encoder must be connected to one of the two Arduino pins
 *   which are interrupt inputs (i.e. pins 2 or 3)
 * 
 *
 */

public class IREncoder implements PIDSource, SpeedEncoder
{
	private int m_port	= -1;
	
	private class IREncoderData
	{
		long	m_pos		= 0;
		int		m_rate		= 0;
		
		private void SetData(long pos, int rate)
		{
			synchronized(this)
			{
				m_pos	= pos;
				m_rate	= rate;
			}
		}
		
		public int GetRate()
		{
			synchronized(this)
			{
				return(m_rate);
			}
		}
		
		public long GetPosition()
		{
			synchronized(this)
			{
				return(m_pos);
			}
		}
	}
	
	private final static int m_maxEncoders = 2;
	private static IREncoderData m_encoders[] = new IREncoderData[m_maxEncoders];
	
	/**
	 * 
	 * @param port - Specifies the encoder port (0 or 1).
	 * 
	 * This system supports up to two IR encoders which should be attached only to either pins 2 or 3
	 * of the Arduino.  The encoder attached to pin 2 is <strong>port 0</strong> and the
	 * encoder attached to pin 3 is <strong>port 1</strong>
	 * 
	 */
	public IREncoder(int port)
	{
		if ((port >= 0) && (port < m_maxEncoders))
		{
			m_port	= port;
			
			if (m_encoders[port] == null)
			{
				m_encoders[port]	= new IREncoderData();

				ArduinoConnection.GetInstance().SendCommand("i" + port);
			}
		}
	}
	
	//! @cond PRIVATE 
	static public void SetData(int encoder, long pos, int rate)
	{
		if ((encoder >= 0) && (encoder < m_maxEncoders))
		{
			if (m_encoders[encoder] != null)
			{
				m_encoders[encoder].SetData(pos, rate);
			}
		}
	}
	//! @endcond
	
	/**
	 * @return Returns the current position of the encoder.
	 * 
	 * Calls <strong>GetPosition()</strong>
	 * 
	 */
	@Override
	public double pidGet() 
	{
		return(GetPosition());
	}
	
	/**
	 * @return Returns the rate (speed) of the motor.
	 */
	@Override
	public int getRate()
	{
		if (m_port >= 0)
		{
			return(m_encoders[m_port].GetRate());
		}
		
		return(0);
	}
	
	/**
	 * @return Returns the current position of the encoder.
	 * 
	 * Note that this encoder is <strong>not</strong> a quadrature encoder, so the
	 * direction of rotation cannot be determined.  Hence this number will
	 * always increase, regardless of which direction the motor is turning.
	 * 
	 */
	public long GetPosition()
	{
		if (m_port >= 0)
		{
			return(m_encoders[m_port].GetPosition());
		}
		
		return(0);
	}

}
