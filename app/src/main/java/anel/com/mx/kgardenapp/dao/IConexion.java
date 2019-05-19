package anel.com.mx.kgardenapp.dao;

import anel.com.mx.kgardenapp.dto.Player;

/**
 * interface
 */
public interface IConexion {
    /**
     *
     */
    public ConexionDAO getConnection();

    /**
     *
     * @param player
     * @return
     */
    public boolean isPlayerExist(Player player);

    /**
     *
     * @return false if the connection fail in error , true
     */
    public boolean closeConnection();



}
