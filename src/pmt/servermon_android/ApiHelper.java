package pmt.servermon_android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import server_classes.Cpu;
import server_classes.Disk;
import server_classes.Network;
import server_classes.Ram;
import server_classes.Server;
import android.os.AsyncTask;
import android.util.Log;

public class ApiHelper {

	public static JSONObject mToken;

	private final String mApiUrl = "http://student20265.201415.uk/pmt/api";

	public ApiHelper() {

	}

	final class AuthTask extends AsyncTask<String, Integer, HttpResponse> {

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(
				"http://student20265.201415.uk/pmt/api/auth/user");
		HttpResponse response;
		JSONObject userJson = new JSONObject();

		@Override
		protected HttpResponse doInBackground(String... params) {

			try {

				userJson.put("email", params[0]);
				userJson.put("password", params[1]);
				userJson.put("is_mobile", true);
				post.setEntity(new StringEntity(userJson.toString()));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				response = httpClient.execute(post);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;
		}

	}

	final class GenericCall extends AsyncTask<String, Integer, HttpResponse> {

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = null;
		HttpResponse response;

		@Override
		protected HttpResponse doInBackground(String... params) {
			post = new HttpPost(params[0]);
			try {

				post.setEntity(new StringEntity(mToken.toString()));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				response = httpClient.execute(post);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;
		}

	}

	private Server jsonToServer(JSONObject json) {
		Log.d("Server", json.toString());
		Server server = null;
		try {
			server = new Server(json.getString("server_id"),
					json.getString("server_name"),
					json.getString("operating_system"),
					json.getString("uptime"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("Server object", server.toString());

		return server;
	}

	public ArrayList<Server> getUserServers(String token) {

		ArrayList<Server> serverList = new ArrayList<Server>();
		String url = mApiUrl + "/user/server/";

		GenericCall get = new GenericCall();
		HttpResponse response = null;
		try {
			response = get.execute(token).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			JSONArray servers = new JSONArray(responseBody);

			for (int i = 0; i < servers.length(); i++) {
				JSONObject server = servers.getJSONObject(i);
				serverList.add(jsonToServer(server));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.d("Servers:", responseBody);

		return serverList;

	}

	public Server getServer(String token, String id) {

		ApiHelper api = new ApiHelper();

		String url = mApiUrl + "/user/server/" + id;
		GenericCall get = new GenericCall();
		try {

			HttpResponse response = get.execute(id).get();
			String responseBody = EntityUtils.toString(response.getEntity());
			Server s = jsonToServer(new JSONObject(responseBody));
			return s;
		} catch (ParseException | JSONException | IOException
				| InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Ram getRam(String id) {
		GenericCall get = new GenericCall();
		HttpResponse response = null;
		Ram ram = new Ram();
		String url = mApiUrl + "/user/server/" + id + "/ram/";

		try {
			response = get.execute(url).get();

			String responseBody = EntityUtils.toString(response.getEntity());
			JSONObject ramObj = new JSONObject(responseBody);
			ram.setTotalRam(ramObj.getString("total_ram"));
			ram.setUsedRam(ramObj.getString("used_ram_mb"));

		} catch (InterruptedException | ExecutionException | ParseException
				| IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ram;
	}

	public Disk getDisk(String id) {
		ApiHelper api = new ApiHelper();
		ApiHelper.GenericCall get = api.new GenericCall();

		HttpResponse response = null;

		Disk disk = new Disk();
		String url = mApiUrl + "/user/server/" + id + "/disk/";

		try {
			response = get.execute(url).get();

			String responseBody = EntityUtils.toString(response.getEntity());
			JSONObject diskObj = new JSONObject(responseBody);

			disk.setTotalSpace(diskObj.getString("total_space"));
			disk.setRemainingSpace(diskObj.getString("remaining_space"));
			disk.setReadSpeed(diskObj.getString("read_speed"));
			disk.setWriteSpeed(diskObj.getString("write_speed"));

		} catch (InterruptedException | ExecutionException | JSONException
				| ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return disk;
	}

	public ArrayList<Disk> getDisks(String id) {
		GenericCall get = new GenericCall();

		HttpResponse response = null;

		ArrayList<Disk> disks = new ArrayList<Disk>();

		String url = mApiUrl + "/user/server/" + id + "/disk/";

		try {
			response = get.execute(url).get();

			String responseBody = EntityUtils.toString(response.getEntity());
			JSONArray diskArr = new JSONArray(responseBody);

			for (int i = 0; i < diskArr.length(); i++) {

				Disk disk = new Disk();

				JSONObject diskObj = diskArr.getJSONObject(i);
				disk.setId(diskObj.getString("id"));
				disk.setTotalSpace(diskObj.getString("total_space"));
				disk.setRemainingSpace(diskObj.getString("remaining_space"));
				disk.setReadSpeed(diskObj.getString("read_speed"));
				disk.setWriteSpeed(diskObj.getString("write_speed"));

				disks.add(disk);
			}

		} catch (InterruptedException | ExecutionException | JSONException
				| ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return disks;
	}

	public Network getNetwork(String id) {
		GenericCall get = new GenericCall();
		HttpResponse response = null;

		Network network = new Network();
		String url = mApiUrl + "/user/server/" + id + "/network/";

		try {
			response = get.execute(url).get();

			String responseBody = EntityUtils.toString(response.getEntity());
			JSONObject netObj = new JSONObject(responseBody);

			network.setHostname(netObj.getString("hostname"));
			network.setIpAddress(netObj.getString("ip_address"));
			network.setGateway(netObj.getString("gateway"));
			network.setPublicIp(netObj.getString("public_ip"));
			network.setUploadTotal(netObj.getString("upload_total"));
			network.setDownloadTotal(netObj.getString("download_total"));

		} catch (InterruptedException | ExecutionException | ParseException
				| IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return network;

	}

	public Cpu getCpu(String id) {

		String url = mApiUrl + "/user/server/" + id + "/cpu/";
		GenericCall get = new GenericCall();
		Cpu cpu = new Cpu();

		HttpResponse response;
		try {
			response = get.execute(url).get();

			String responseBody = EntityUtils.toString(response.getEntity());
			JSONObject cpuObj;

			cpuObj = new JSONObject(responseBody);

			// cpu.setId(cpuObj.getString("id"));
			cpu.setVendor(cpuObj.getString("vendor"));
			cpu.setModel(cpuObj.getString("model"));
			cpu.setClock_speed(cpuObj.getString("clock_speed"));
			cpu.setCpu_usage_percentage(cpuObj
					.getString("cpu_usage_percentage"));

		} catch (InterruptedException | ExecutionException | ParseException
				| IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cpu;

	}

	public ArrayList<Cpu> getCpus(String id) {

		String url = mApiUrl + "/user/server/" + id + "/cpu/";
		GenericCall get = new GenericCall();

		ArrayList<Cpu> cpus = new ArrayList<Cpu>();
		Cpu cpu = new Cpu();
		HttpResponse response;

		try {
			response = get.execute(url).get();

			String responseBody = EntityUtils.toString(response.getEntity());
			JSONObject cpuObj;

			JSONArray cpuArray = new JSONArray(responseBody);

			for (int i = 0; i < cpuArray.length(); i++) {
				cpuObj = cpuArray.getJSONObject(i);
				cpu.setId(cpuObj.getString("id"));
				cpu.setVendor(cpuObj.getString("vendor"));
				cpu.setModel(cpuObj.getString("model"));
				cpu.setClock_speed(cpuObj.getString("clock_speed"));
				cpu.setCpu_usage_percentage(cpuObj
						.getString("cpu_usage_percentage"));
			}

		} catch (InterruptedException | ExecutionException | ParseException
				| IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cpus;
	}

	/**
	 * 
	 * 
	 * come back to this
	 * 
	 * @param id
	 */
	public void getCpuHistory(final String id) {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				String url = "http://student20265.201415.uk/pmt/api/user/server/"
						+ id + "/cpu/list/";
				ApiHelper api = new ApiHelper();
				ApiHelper.GenericCall get = api.new GenericCall();

				ArrayList<Cpu> cpus = new ArrayList<Cpu>();
				int averageCpu = 0;

				Cpu cpu = new Cpu();

				HttpResponse response;
				String responseBody = null;

				try {
					response = get.execute(url).get();

					responseBody = EntityUtils.toString(response.getEntity());

				} catch (InterruptedException | ExecutionException
						| ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				JSONArray cpuHistory = null;

				try {
					cpuHistory = new JSONArray(responseBody);

					for (int i = 0; i < cpuHistory.length(); i++) {

						if (cpuHistory.getJSONObject(i).equals(null))
							break;

						averageCpu += cpuHistory.getJSONObject(i).getInt(
								"cpu_usage_percentage");
					}
					averageCpu /= cpuHistory.length();

				} catch (JSONException e) {
					// TODO Auto-generated catch block
				}

			}

		};
		new Thread(run).start();
	}

	public JSONArray getRamHistory(final String id) {

		String url = mApiUrl + "/user/server/" + id + "/ram/list/";
		GenericCall get = new GenericCall();

		HttpResponse response;
		String responseBody = null;

		try {
			response = get.execute(url).get();
			responseBody = EntityUtils.toString(response.getEntity());
		} catch (InterruptedException | ExecutionException | ParseException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		try {
			return new JSONArray(responseBody);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public JSONArray getCpuHistoryArray(final String id) {

		String url = mApiUrl + "/user/server/" + id + "/cpu/list/";

		GenericCall get = new GenericCall();

		HttpResponse response;
		String responseBody = null;

		try {
			response = get.execute(url).get();
			responseBody = EntityUtils.toString(response.getEntity());
		} catch (InterruptedException | ExecutionException | ParseException
				| IOException e) {
			e.printStackTrace();

		}

		try {
			return new JSONArray(responseBody);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;

	}

	public String login(String email, String password)
			throws InterruptedException, ExecutionException {

		AuthTask auth = new AuthTask();

		HttpResponse response = auth.execute(email, password).get();

		String responseBody = null;
		String tokenString = null;

		try {
			responseBody = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}

		try {
			JSONObject token = new JSONObject(responseBody);
			if(token.get("token") != null){
				tokenString = token.get("token").toString();
				return tokenString;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return tokenString;

	}

}
