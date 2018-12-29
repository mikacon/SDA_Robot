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

public class AnalogEncoders 
{
	private static AnalogEncoderData[] m_data = new AnalogEncoderData[2];
	private static AnalogEncoders m_encoders = new AnalogEncoders();
	
	public class AnalogEncoderData
	{
		private int m_position = 0;
		private int m_rate = 0;
		private int m_zero = 0;
		private boolean m_init = true;
		
		void SetData(int position, int rate)
		{
			synchronized(this)
			{
				m_position	= position;
				m_rate		= rate;
				
				if (m_init)
				{
					m_zero	= m_position;
					m_init	= false;
//					System.out.println("encode init: zero = " + m_zero);
				}
			}
		}
		
		public int GetPosition()
		{
			synchronized(this)
			{
				return(m_position - m_zero);
			}
		}
		
		public int GetRate()
		{
			synchronized(this)
			{
				return(m_rate);
			}
		}
	}
	
	public static AnalogEncoders GetInstance()
	{
		return(m_encoders);
	}
	
	public AnalogEncoderData GetData(int port)
	{
		if ((port >= 0) && (port < 2))
		{
			if (m_data[port] == null)
			{
//				System.out.println("Enable encoder " + port);
				
				m_data[port]	= m_encoders.new AnalogEncoderData();
				
				ArduinoConnection.GetInstance().SendCommand("e " + port);
			}
			
			return(m_data[port]);
		}
		
		return(null);
	}
	
	public void SetData(int port, int position, int rate)
	{
		AnalogEncoderData	data = GetData(port);
		
//		System.out.print("port=");System.out.print(port);
//		System.out.print(",pos=");System.out.print(position);
//		System.out.print(",rate=");System.out.println(rate);
		
		if (data != null)
		{
			data.SetData(position, rate);
		}
	}

}
