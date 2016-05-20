/**
 * Sep 13, 2012 11:57:33 AM
 */
package org.surreal.lobster.sharedcore.nosql;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.Cassandra.truncate_args;
import org.apache.cassandra.thrift.Cassandra.truncate_result;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.UnavailableException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A limited-capability Cassandra Client lifted liberally from Apache's <i>Cassandra Thrift</i> 
 * client.  This client can perform several operations once connected to the given keyspace.
 * Use  {@link #connect(String)} to switch keyspaces on the same server instance.  Invoke 
 * {@link #disconnect()} when finished.
 * @see CassandraConnection
 * @author daniel.wilkin
 * @deprecated No Cassandra server instance resides on the server
 */
@Deprecated
public class CassandraThriftZombie {
	/** The LOGGER instance for this class */
	private static Logger LOGGER = LoggerFactory.getLogger(CassandraThriftZombie.class);
	
	protected TProtocol oprot_;
	protected TProtocol iprot_;
	protected TTransport transport;
	
	protected int seqid_;

	private final String host;
	private final int port;
	
	/**
	 * Creates a new instance with intent to connect to specified host and port
	 * @param host The host of the Cassandra server
	 * @param port The port for the Cassandra server
	 */
	public CassandraThriftZombie(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	/**
	 * Connects to the specified keyspace
	 * @param keyspace The name of the keyspace
	 * @return <code>True</code> if the connection was successful, <code>false</code> otherwise
	 */
	public boolean connect(String keyspace) {
		disconnect();
		
		transport = new TFramedTransport(new TSocket(host, port));
		TBinaryProtocol protocol = new TBinaryProtocol(transport);
		try {
			transport.open();
			oprot_ = protocol;
			iprot_ = protocol;
			// connected, switch keyspace
			Cassandra.Client client = new Cassandra.Client(protocol);
			client.set_keyspace(keyspace);
			return true;
		} catch (TTransportException e) {
			LOGGER.error(String.format("Failed to connect to %s:%d", host, port), e);
		} catch (InvalidRequestException e) {
			LOGGER.error(String.format("Switching to keyspace '%s' failed: %s:[%s]",
					keyspace, e.getClass().getSimpleName(), e.getMessage()));
		} catch (TException e) {
			LOGGER.error(String.format("Switching to keyspace '%s' failed: %s:[%s]",
					keyspace, e.getClass().getSimpleName(), e.getMessage()));
		}
		return false;
	}

	/**
	 * Always disconnect.
	 */
	public void disconnect() {
		if (transport != null) {
			transport.close();
			transport = null;
		}
	}
	
	/**
	 * Truncates the data from the specified Cassandra column family (super or otherwise).
	 * @param topic Column family name
	 */
	public void truncate(NoSQLTopic topic) {
		String msg = String.format("Failed to truncate column family %s", topic.getName());
		try {
			truncate(topic.getName());
		} catch (InvalidRequestException e) {
			LOGGER.error(msg, e);
		} catch (UnavailableException e) {
			LOGGER.error(msg.concat(": UnavailableException"));
		} catch (TException e) {
			LOGGER.error(msg.concat(String.format(": TException:[%s]", e.getMessage())));
		}
	}

	private void truncate(String cfname) throws InvalidRequestException, UnavailableException, org.apache.thrift.TException {
      send_truncate(cfname);
      recv_truncate();
    }

    private void send_truncate(String cfname) throws org.apache.thrift.TException {
      oprot_.writeMessageBegin(new org.apache.thrift.protocol.TMessage("truncate", org.apache.thrift.protocol.TMessageType.CALL, ++seqid_));
      truncate_args args = new truncate_args();
      args.setCfname(cfname);
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    private void recv_truncate() throws InvalidRequestException, UnavailableException, org.apache.thrift.TException {
      org.apache.thrift.protocol.TMessage msg = iprot_.readMessageBegin();
      if (msg.type == org.apache.thrift.protocol.TMessageType.EXCEPTION) {
        org.apache.thrift.TApplicationException x = org.apache.thrift.TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      if (msg.seqid != seqid_) {
        throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.BAD_SEQUENCE_ID, "truncate failed: out of sequence response");
      }
      truncate_result result = new truncate_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.ire != null) {
        throw result.ire;
      }
      if (result.ue != null) {
        throw result.ue;
      }
      return;
    }
}
